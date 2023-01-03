/*
 * Provide basic CRUD on top of SQLite + Sessions extension
 * Author: Joshua Rowe
 */
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

typedef struct user_t {
  const char* name;
  bool enabled;
} user_t;

typedef struct users_result {
  int len;
  user_t *users; 
} users_result;

typedef enum core_op_result {
  Success,
  ValidationError,
  DBError
} core_op_result;


int init_table();
users_result get_users(int limit, int offset);
core_op_result create_user(char* name, bool enabled);