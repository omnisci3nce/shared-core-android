#include "../include/core.h"

int main() {
  init_table();

  create_user("Admin", true);
  create_user("Darth Vader", false);
  create_user("Josh", true);

  users_result result = get_users(10, 0);
  printf("%d users found\n", result.len);

  for (int i = 0; i < result.len; i++) {
    printf("User [name='%s'] [enabled=%s]\n", result.users[i].name, result.users[i].enabled ? "true" : "false");
  }

  return 0;
}