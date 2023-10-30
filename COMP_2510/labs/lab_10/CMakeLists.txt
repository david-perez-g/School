cmake_minimum_required(VERSION 3.0.0)
project(lab_10 VERSION 0.1.0 LANGUAGES C)

set(CMAKE_C_STANDARD 99)

add_library(linked_list STATIC ../../data_structures/linked_list.c)
add_executable(lab_10 main.c)
target_link_libraries(lab_10 linked_list)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
