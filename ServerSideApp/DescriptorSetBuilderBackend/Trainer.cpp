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
    Mat img, descriptors;
    float scaleX, scaleY;
    scaleX = IMAGE_WIDTH / _img.cols;
    scaleY = IMAGE_HEIGHT / _img.rows;
    resize(_img, img, Size(), scaleX, scaleY);
    vector<KeyPoint> keypoints;
    detector_agast->detect(img, keypoints);
    extractor->compute(img, keypoints, descriptors);
    images.push_back(descriptors);
    descriptor_count += descriptors.rows;
    return descriptors.rows;
}

void Trainer::train() {
    int mat_cols = images.front().cols;
    int mat_type = images.front().type();
    int mat_rows = descriptor_count;
    Mat descriptors = Mat::zeros(mat_rows, mat_cols, mat_type);
    for (int i = 0; i < images.size(); i++) {
        descriptors.push_back(images[i]);
    }
    index = new cv::flann::Index(descriptors, INDEX_PARAM , cvflann::FLANN_DIST_HAMMING);
}