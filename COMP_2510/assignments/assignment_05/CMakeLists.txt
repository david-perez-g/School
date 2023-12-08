cmake_minimum_required(VERSION 3.0.0)
project(assigment_05 VERSION 0.1.0 LANGUAGES C)

set(CMAKE_C_STANDARD 99)

include(CTest)
enable_testing()

add_executable(assigment_05 main.c)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
