cmake_minimum_required(VERSION 3.0.0)
project(memory_management VERSION 0.1.0 LANGUAGES C)

include(CTest)
enable_testing()

add_library(memory_management memory_management.c)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)

add_executable(test_memory_management test_memory_management.c)
enable_testing()
add_test(NAME test_memory_management COMMAND $<TARGET_FILE:test_memory_management>)