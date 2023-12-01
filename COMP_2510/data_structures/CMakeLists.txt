cmake_minimum_required(VERSION 3.0.0)
project(data_structures VERSION 0.1.0 LANGUAGES C)

set(CMAKE_C_STANDARD 99)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)

add_library(linked_list STATIC linked_list.c)
add_executable(linked_list_test test_linked_list.c)
target_link_libraries(linked_list_test linked_list)

add_library(stack STATIC stack.c)
add_executable(stack_test test_stack.c)
target_link_libraries(stack_test stack)

add_library(bst STATIC bst.c)
add_executable(bst_test test_bst.c)
target_link_libraries(bst_test bst linked_list stack)

include(CTest)
enable_testing()
add_test(NAME linked_list_test COMMAND $<TARGET_FILE:linked_list_test>)
add_test(NAME stack_test COMMAND $<TARGET_FILE:stack_test>)
add_test(NAME bst_test COMMAND $<TARGET_FILE:bst_test>)