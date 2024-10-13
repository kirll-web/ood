cmake_minimum_required(VERSION 3.29)
project(streamsLion)

set(CMAKE_CXX_STANDARD 20)

add_executable(streamsLion main.cpp
        IInputDataStream.h
        cmake-build-debug/IInputDataStream.h)
