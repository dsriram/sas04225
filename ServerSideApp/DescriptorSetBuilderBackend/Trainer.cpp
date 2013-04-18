#include "Trainer.hpp"

using namespace org::sas04225;
using namespace cv;
using namespace std;

Trainer::Trainer() {
    descriptor_count = 0;
    detector_agast = new cv::BRISK(AGAST_PARAM);
    extractor = new cv::FREAK(FREAK_PARAM);
}

uint32_t Trainer::addImage(string image) {
    Mat _img = imread(image, CV_LOAD_IMAGE_GRAYSCALE);
    Mat img, _descriptors;
    float scaleX, scaleY;
    scaleX = IMAGE_WIDTH / _img.cols;
    scaleY = IMAGE_HEIGHT / _img.rows;
    resize(_img, img, Size(), scaleX, scaleY);
    vector<KeyPoint> keypoints;
    detector_agast->detect(img, keypoints);
//    SurfFeatureDetector detector(SURF_PARAM);
//     detector.detect(img,keypoints);
    extractor->compute(img, keypoints, _descriptors);
//    SurfDescriptorExtractor extractor_surf;
//    detector.compute(img,keypoints,_descriptors);
    images.push_back(_descriptors);
    descriptor_count += _descriptors.rows;
    return _descriptors.rows;
}

uint32_t Trainer::addImages(vector<string> files) {
    Mat _descriptors_temp, _descriptors;
    for (int i = 0; i < files.size()-1; i++) {
        Mat _img = imread(files[i], CV_LOAD_IMAGE_GRAYSCALE);
        Mat img, _img_descriptors;
        float scaleX, scaleY;
        scaleX = IMAGE_WIDTH / _img.cols;
        scaleY = IMAGE_HEIGHT / _img.rows;
        resize(_img, img, Size(), scaleX, scaleY);
        vector<KeyPoint> keypoints;
            detector_agast->detect(img, keypoints);
//        SurfFeatureDetector detector(SURF_PARAM);
//        detector.detect(img, keypoints);
        extractor->compute(img, keypoints, _img_descriptors);
        _descriptors_temp.push_back(_img_descriptors);
        //    SurfDescriptorExtractor extractor_surf;
        //    detector.compute(img,keypoints,_descriptors);
    }
    Mat _img = imread(files[files.size()-1], CV_LOAD_IMAGE_GRAYSCALE);
    Mat img, _img_descriptors;
    float scaleX, scaleY;
    scaleX = IMAGE_WIDTH / _img.cols;
    scaleY = IMAGE_HEIGHT / _img.rows;
    resize(_img, img, Size(), scaleX, scaleY);
    vector<KeyPoint> keypoints;
        detector_agast->detect(img, keypoints);
//    SurfFeatureDetector detector(SURF_PARAM);
//    detector.detect(img, keypoints);
    extractor->compute(img, keypoints, _img_descriptors);
    BruteForceMatcher<Hamming> matcher;
    vector<DMatch> matches;
    matcher.match(_img_descriptors, _descriptors_temp, matches);
    for (int i = 0; i < matches.size(); i++) {
        DMatch m = matches[i];
        if (m.distance < 80) {
            _descriptors.push_back(_descriptors_temp.row(m.trainIdx));
            _descriptors.push_back(_img_descriptors.row(m.queryIdx));
        }
    }
    descriptor_count += _descriptors.rows;
    images.push_back(_descriptors);
    return _descriptors.rows;
}

void Trainer::train() {
    int mat_cols = images.front().cols;
    int mat_type = images.front().type();
    int mat_rows = descriptor_count;
    //Mat descriptors = Mat::zeros(1, mat_cols, mat_type);
    for (int i = 0; i < images.size(); i++) {
        descriptors.push_back(images[i]);
    }
    index = new cv::flann::Index(descriptors, INDEX_PARAM , cvflann::FLANN_DIST_HAMMING);
}

void Trainer::save(string filename) {
    index->save(filename);
}