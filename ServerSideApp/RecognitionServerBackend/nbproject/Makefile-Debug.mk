#
# Generated Makefile - do not edit!
#
# Edit the Makefile in the project folder instead (../Makefile). Each target
# has a -pre and a -post target defined where you can add customized code.
#
# This makefile implements configuration specific macros and targets.


# Environment
MKDIR=mkdir
CP=cp
GREP=grep
NM=nm
CCADMIN=CCadmin
RANLIB=ranlib
CC=icc
CCC=icpc
CXX=icpc
FC=ifort
AS=as

# Macros
CND_PLATFORM=Intel-Linux-x86
CND_DLIB_EXT=so
CND_CONF=Debug
CND_DISTDIR=dist
CND_BUILDDIR=build

# Include project Makefile
include Makefile

# Object Directory
OBJECTDIR=${CND_BUILDDIR}/${CND_CONF}/${CND_PLATFORM}

# Object Files
OBJECTFILES= \
	${OBJECTDIR}/ImageLookupWorker.o \
	${OBJECTDIR}/RecognitionServerBackendInit.pb.o \
	${OBJECTDIR}/LookupResult.pb.o \
	${OBJECTDIR}/main.o \
	${OBJECTDIR}/LookupQuery.pb.o


# C Compiler Flags
CFLAGS=-m64 -O3 -parallel -openmp

# CC Compiler Flags
CCFLAGS=-m64 -O3 -parallel -openmp
CXXFLAGS=-m64 -O3 -parallel -openmp

# Fortran Compiler Flags
FFLAGS=

# Assembler Flags
ASFLAGS=--64

# Link Libraries and Options
LDLIBSOPTIONS=`pkg-config --libs opencv` `pkg-config --libs protobuf` /opt/intel/composer_xe_2013.1.117/compiler/lib/intel64/libiomp5.so  

# Build Targets
.build-conf: ${BUILD_SUBPROJECTS}
	"${MAKE}"  -f nbproject/Makefile-${CND_CONF}.mk ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/recognitionserverbackend

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/recognitionserverbackend: /opt/intel/composer_xe_2013.1.117/compiler/lib/intel64/libiomp5.so

${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/recognitionserverbackend: ${OBJECTFILES}
	${MKDIR} -p ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}
	icpc -o ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/recognitionserverbackend ${OBJECTFILES} ${LDLIBSOPTIONS} 

${OBJECTDIR}/ImageLookupWorker.o: ImageLookupWorker.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g `pkg-config --cflags opencv` `pkg-config --cflags protobuf` -std=c++11   -MMD -MP -MF $@.d -o ${OBJECTDIR}/ImageLookupWorker.o ImageLookupWorker.cpp

${OBJECTDIR}/RecognitionServerBackendInit.pb.o: RecognitionServerBackendInit.pb.cc 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g `pkg-config --cflags opencv` `pkg-config --cflags protobuf` -std=c++11   -MMD -MP -MF $@.d -o ${OBJECTDIR}/RecognitionServerBackendInit.pb.o RecognitionServerBackendInit.pb.cc

${OBJECTDIR}/LookupResult.pb.o: LookupResult.pb.cc 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g `pkg-config --cflags opencv` `pkg-config --cflags protobuf` -std=c++11   -MMD -MP -MF $@.d -o ${OBJECTDIR}/LookupResult.pb.o LookupResult.pb.cc

${OBJECTDIR}/main.o: main.cpp 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g `pkg-config --cflags opencv` `pkg-config --cflags protobuf` -std=c++11   -MMD -MP -MF $@.d -o ${OBJECTDIR}/main.o main.cpp

${OBJECTDIR}/LookupQuery.pb.o: LookupQuery.pb.cc 
	${MKDIR} -p ${OBJECTDIR}
	${RM} $@.d
	$(COMPILE.cc) -g `pkg-config --cflags opencv` `pkg-config --cflags protobuf` -std=c++11   -MMD -MP -MF $@.d -o ${OBJECTDIR}/LookupQuery.pb.o LookupQuery.pb.cc

# Subprojects
.build-subprojects:

# Clean Targets
.clean-conf: ${CLEAN_SUBPROJECTS}
	${RM} -r ${CND_BUILDDIR}/${CND_CONF}
	${RM} ${CND_DISTDIR}/${CND_CONF}/${CND_PLATFORM}/recognitionserverbackend

# Subprojects
.clean-subprojects:

# Enable dependency checking
.dep.inc: .depcheck-impl

include .dep.inc
