#include "ImageLookupWorker.hpp"
#include "LookupResult.pb.h"

using namespace org::sas04225::RecognitionServerBackend;
using namespace cv;

int ImageLookupWorker::k = 8;
int ImageLookupWorker::dist_threshold = 75;

cv::FREAK extractor(true, true, 22.0f, 4);
cv::BRISK detector_agast(30, 5);

ImageLookupWorker::ImageLookupWorker(std::string queryImage, uint64_t request_id) {
    this->queryImage = queryImage;
    this->request_id = request_id;
}

void ImageLookupWorker::computeDescriptors() {
    Mat _query_img = imread(queryImage, CV_LOAD_IMAGE_GRAYSCALE);
    float scalex = 640.0 / _query_img.cols;
    float scaley = 480.0 / _query_img.rows;
    Mat query_img;
    cv::resize(_query_img, query_img, Size(), scalex, scaley);
    vector<KeyPoint> keypoints;
    detector_agast.detect(query_img, keypoints);
    extractor.compute(query_img, keypoints, descriptors);
}

void ImageLookupWorker::lookup(cv::flann::Index* index) {
    Mat results = cv::Mat(descriptors.rows, k, CV_32SC1); // Results index
    Mat dists = cv::Mat(descriptors.rows, k, CV_32SC1);
    index->knnSearch(descriptors, results, dists, k, cv::flann::SearchParams());
    queryResult = new std::vector<DMatch > ();
    for (int i = 0; i < descriptors.rows; i++) {
        for (int j = 0; j < k; j++) {
            if (dists.at<int>(i, j) <= dist_threshold) {
                DMatch match;
                match.distance = dists.at<int>(i, j);
                match.queryIdx = i;
                match.trainIdx = results.at<int>(i, j);
                match.imgIdx = 0;
                queryResult->push_back(match);
            }
        }
    }
}

org::sas04225::proto::LookupResult ImageLookupWorker::getResult() {
    org::sas04225::proto::LookupResult result;
    result.Clear();
    result.set_request_id(this->request_id);
    for (int i = 0; i < queryResult->size(); i++) {
        cv::DMatch match = queryResult->at(i);
        org::sas04225::proto::DMatch* dmatch = result.add_matches();
        dmatch->set_distance((uint32_t)match.distance);
        dmatch->set_trainidx(match.trainIdx);
    }
    return result;
}