/* 
 * File:   MatchResultAnalytics.hpp
 * Author: sriram
 *
 * Created on February 2, 2013, 8:49 PM
 */

#ifndef MATCHRESULTANALYTICS_HPP
#define	MATCHRESULTANALYTICS_HPP

#include <vector>
#include <opencv2/opencv.hpp>

namespace org {
    namespace sas04225 {

        struct DMatchAnalyticsResult {
        public:
            float percent;
            int distance;
            int count; //no.of matches less than 'distance'
        };

        class DMatchAnalytics {
        public:
            DMatchAnalytics(const std::vector<cv::DMatch>& matches);
            std::vector<DMatchAnalyticsResult> computeAnalytics(const int no_of_sample_points);

        private:
            std::vector<cv::DMatch> matches;
        };

    }
}
#endif	/* MATCHRESULTANALYTICS_HPP */

