cmake_minimum_required(VERSION 3.0.0)
project(memory VERSION 0.1.0 LANGUAGES C)

include(CTest)
enable_testing()

add_library(memory STATIC memory.c)
add_executable(test_memory test_memory.c)
target_link_libraries(test_memory memory)

set(CPACK_PROJECT_NAME ${PROJECT_NAME})
set(CPACK_PROJECT_VERSION ${PROJECT_VERSION})
include(CPack)

add_test(NAME test_memory COMMAND $<TARGET_FILE:test_memory>)
