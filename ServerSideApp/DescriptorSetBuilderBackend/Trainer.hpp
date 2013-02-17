/* 
 * File:   Trainer.hpp
 * Author: sriram
 *
 * Created on February 16, 2013, 7:25 PM
 */

#ifndef TRAINER_HPP
#define	TRAINER_HPP

#include <iostream>
#include <string>
#include <vector>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/legacy/legacy.hpp>
#include <opencv/cv.h>
#include <opencv2/flann/flann.hpp>

#include "MatchResultAnalytics.hpp"

#define AGAST_PARAM 30,5
#define FREAK_PARAM true, true, 22.0f, 4
#define INDEX_PARAM cv::flann::LshIndexParams(1, 20, 2)

#define IMAGE_WIDTH 1024.0
#define IMAGE_HEIGHT 768.0

using namespace cv;
using namespace std;
//using namespace org::sas04225;

namespace org{
    namespace sas04225{
        
        class Trainer{
        private:
            cv::BRISK* detector_agast;
            cv::FREAK* extractor;
            cv::flann::Index* index;
            int k = 1;
            vector<string> files;
            vector<Mat> images;
        
        public:
            uint32_t descriptor_count;
            
            Trainer();
            
            //Return computed descriptors count for the image
            uint32_t addImage(string image);
            
            void train();
        };
    }
}



#endif	/* TRAINER_HPP */

