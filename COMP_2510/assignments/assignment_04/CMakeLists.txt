cmake_minimum_required(VERSION 3.0.0)
project(assignment_04 VERSION 0.1.0 LANGUAGES C)

include(CTest)
enable_testing()

add_library(bst STATIC ../../data_structures/bst.c)
add_library(stack STATIC ../../data_structures/stack.c)
add_library(linked_list STATIC ../../data_structures/linked_list.c)
add_executable(assignment_04 main.c)
target_link_libraries(assignment_04 bst stack linked_list)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)
