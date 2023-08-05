cmake_minimum_required(VERSION 3.0.0)
project(linked_list VERSION 0.1.0 LANGUAGES C)

include(CTest)

add_library(linked_list SHARED linked_list.c)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)

add_executable(linked_list_test test_linked_list.c)
enable_testing()
add_test(NAME linked_list_test COMMAND $<TARGET_FILE:linked_list_test>)