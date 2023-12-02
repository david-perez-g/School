cmake_minimum_required(VERSION 3.0.0)
project(lab_12 VERSION 0.1.0 LANGUAGES C)

set(CMAKE_C_STANDARD 99)

add_library(dllist STATIC ../../data_structures/dllist.c)
add_executable(lab_12 main.c)
target_link_libraries(lab_12 dllist)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
