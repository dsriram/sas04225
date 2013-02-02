/*
 * File:   main.cpp
 * Author: sriram
 *
 * Created on January 5, 2013, 8:00 PM
 */

//#define CV_SSSE3

#include <iostream>
#include <string>
#include <vector>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/legacy/legacy.hpp>
#include <opencv/cv.h>

//#include "freak.h"
//#include "hammingseg.h"

using namespace cv;

void floodFillPostprocess( Mat& img, const Scalar& colorDiff=Scalar::all(1) )
{
    CV_Assert( !img.empty() );
    RNG rng = theRNG();
    Mat mask( img.rows+2, img.cols+2, CV_8UC1, Scalar::all(0) );
    for( int y = 0; y < img.rows; y++ )
    {
        for( int x = 0; x < img.cols; x++ )
        {
            if( mask.at<uchar>(y+1, x+1) == 0 )
            {
                Scalar newVal( rng(256), rng(256), rng(256) );
                floodFill( img, mask, Point(x,y), newVal, 0, colorDiff, colorDiff );
                //cout<<
            }
        }
    }
}

int main(int argc, char** argv) {
    // check http://opencv.itseez.com/doc/tutorials/features2d/table_of_content_features2d/table_of_content_features2d.html
    // for OpenCV general detection/matching framework details

    //    if( argc != 3 ) {
    //        help(argv);
    //        return -1;
    //    }

    // Load images
    Mat _imgA = imread("/home/sriram/Development/Databases/camera_repo/samsung-cdma/1.jpg", CV_LOAD_IMAGE_GRAYSCALE);
    Mat _imgC = imread("/home/sriram/Development/Databases/camera_repo/samsung-cdma/0.jpg", CV_LOAD_IMAGE_GRAYSCALE);
    Mat imgA, imgC;
    float scalex_A,scalex_C,scaley_A,scaley_C;
    scalex_A = 640.0/_imgA.cols;
    scalex_C = 640.0/_imgC.cols;
    scaley_A = 480.0/_imgA.rows;
    scaley_C = 480.0/_imgC.rows;
    resize(_imgA, imgA, Size(), scalex_A, scaley_A);
    resize(_imgC, imgC, Size(), scalex_C, scaley_C);
    
//     Mat imX = Mat::zeros(imgA.size(), CV_8UC3);
//    Mat imY = Mat::zeros(imgA.size(), CV_8UC3);
//    Mat imZ = Mat::zeros(imgA.size(), CV_8UC1);
//    Mat chs[3];
//    chs[0] = chs[1] = chs[2] = imgA;
//    cv::merge(chs, 3, imX);
//    IplImage img1 = imX;
//    IplImage img2 = imY;
//    cvPyrMeanShiftFiltering(&img1, &img2, 20, 40, 2);
//    cv::threshold(imY, imZ, 100, 255, 1);
//    cv::cvtColor(imZ,imZ,CV_RGB2GRAY,1);
//    int dilation_type,dilation_size;
//    dilation_type = cv::MORPH_RECT;
//    dilation_size = 2;
//    Mat element = getStructuringElement(dilation_type, Size(2 * dilation_size + 1, 2 * dilation_size + 1), Point(dilation_size, dilation_size));
//    cv::dilate(imZ,imZ,element);
//    img1 = imgA;
//    img2 = imZ;
//    
//    
//    
//    //cvAnd(&img1,&img2,&img1);
//    //floodFillPostprocess(imZ);//,Scalar::all(2));
//    namedWindow("segmented", CV_WINDOW_KEEPRATIO);
//    imshow("segmented", imZ);

    std::vector<KeyPoint> keypointsA, keypointsB;
    Mat descriptorsA, descriptorsB;
    std::vector<DMatch> matches;
    std::vector<Mat> images;
    std::vector<std::vector<KeyPoint> > keypts;
    keypts.push_back(keypointsA);
    //keypts.push_back(keypointsB);
    images.push_back(imgA);
    //images.push_back(imgC);
    
    cv::BRISK detector_agast;
    
    // DETECTION
    // Any openCV detector such as
    SurfFeatureDetector detector0(4000, 4,2,true);
    cv::FastFeatureDetector detector1(80);
    cv::StarDetector detector2;
    cv::Ptr<FeatureDetector> detector_ptr(new cv::FastFeatureDetector(60));
//    cv::Ptr<FeatureDetector> detector_ptr(new cv::StarDetector());
    cv::PyramidAdaptedFeatureDetector detector(detector_ptr, 7);


    // DESCRIPTOR
    // Our proposed FREAK descriptor
    // (roation invariance, scale invariance, pattern radius corresponding to SMALLEST_KP_SIZE,
    // number of octaves, optional vector containing the selected pairs)
    // FREAK extractor(true, true, 22, 4, std::vector<int>());
    FREAK extractor(false, true, 22.0f, 7);

    // MATCHER
    // The standard Hamming distance can be used such as
    // BruteForceMatcher<Hamming> matcher;
    // or the proposed cascade of hamming distance using SSSE3
#if CV_SSSE3
    BruteForceMatcher< HammingSeg < 30, 4 > > matcher;
#else
    BruteForceMatcher<Hamming> matcher;
#endif

    // detect
    double t = (double) getTickCount();
    detector_agast.detect(imgA, keypointsA);
    detector_agast.detect(imgC, keypointsB);
    SurfDescriptorExtractor extractor0;

    //    Mat descriptors_1, descriptors_2;

    //extractor.selectPairs(images,keypts,0.0,true);
    t = ((double) getTickCount() - t) / getTickFrequency();
    std::cout << "detection time [s]: " << t / 1.0 << std::endl;

    // extract
    t = (double) getTickCount();
    extractor.compute(imgA, keypointsA, descriptorsA);
    extractor.compute(imgC, keypointsB, descriptorsB);
    //    extractor0.compute( imgA, keypointsA, descriptorsA );
    //    extractor0.compute( imgC, keypointsB, descriptorsB );
    t = ((double) getTickCount() - t) / getTickFrequency();
    std::cout << "extraction time [s]: " << t << std::endl;

    // match
    t = (double) getTickCount();
    BruteForceMatcher< L2<float> > matcher0;
    matcher.match(descriptorsA, descriptorsB, matches);
    t = ((double) getTickCount() - t) / getTickFrequency();
    std::cout << "matching time [s]: " << t << std::endl;
    std::cout << matches.size() << std::endl;

    // Draw matches
    std::cout<<"KeypointsA count:"<<keypointsA.size()<<std::endl;
    std::cout<<"KeypointsB count:"<<keypointsB.size()<<std::endl;
    Mat imgMatch;
    std::vector<DMatch> matches0;
    std::cout<<"Matches count:"<<matches.size()<<std::endl;
    for (int i = 0; i < matches.size(); i++) {
        std::cout << matches[i].distance<<" ";
        if (matches[i].distance < 80)
        {
            matches0.push_back(matches[i]);
            //std::cout<<" "<<matches[i].queryIdx;
        }
    }
    drawMatches(imgA, keypointsA, imgC, keypointsB, matches0, imgMatch,Scalar::all(-1),Scalar::all(-1),vector<char>(),DrawMatchesFlags::NOT_DRAW_SINGLE_POINTS);
    detector_ptr.release();
    namedWindow("matches", CV_WINDOW_KEEPRATIO);
    imshow("matches", imgMatch);
    waitKey(0);
}

