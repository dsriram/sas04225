/* 
 * File:   msgIO.hpp
 * Author: sriram
 *
 * Created on February 18, 2013, 1:07 PM
 */

#ifndef MSGIO_HPP
#define	MSGIO_HPP

#include <fcntl.h>
#include <unistd.h>

static unsigned char buffer[65535];

template<class T>
void writeToFD(T& msg, int fd) {
    uint32_t len = msg.ByteSize();
    write(fd,(void*)(&len),4);
    msg.SerializeToArray((void*) buffer, len);
    write(fd,buffer,len);
}

template<class T>
T& readFromFD(T& msg, int fd) {
    uint32_t len = 0;
    read(fd,(void*)&len,4);
    read(fd,(void*)&buffer[0],len);
    msg.ParseFromArray(buffer, len);
    return msg;
}


#endif	/* MSGIO_HPP */

