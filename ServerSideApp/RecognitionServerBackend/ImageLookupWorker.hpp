/* 
 * File:   ImageLookupWorker.hpp
 * Author: sriram
 *
 * Created on February 18, 2013, 8:24 PM
 */

#ifndef IMAGELOOKUPWORKER_HPP
#define	IMAGELOOKUPWORKER_HPP

#include <string>
#include <stdint.h>

#include <vector>

#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/nonfree/features2d.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <opencv2/legacy/legacy.hpp>
#include <opencv/cv.h>
#include <opencv2/flann/flann.hpp>

#include "LookupResult.pb.h"

namespace org {
    namespace sas04225 {
        namespace RecognitionServerBackend {

            class ImageLookupWorker {
            private:
                uint64_t request_id;
                std::string queryImage;
                cv::Mat descriptors;
                std::vector<cv::DMatch> *queryResult;
                
            public:
                
                static int k;
                static int dist_threshold;
                
                ImageLookupWorker(std::string queryImage, uint64_t request_id);
                
                void computeDescriptors();
                
                void lookup(cv::flann::Index* index);
                
                org::sas04225::proto::LookupResult getResult();

            };

        }
    }

}


#endif	/* IMAGELOOKUPWORKER_HPP */

