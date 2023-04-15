cmake_minimum_required(VERSION 3.00)
project(Lab8)

include(CTest)
enable_testing()

add_executable(Lab8 main.c)

set(CMAKE_CCOMPIRE)
set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
