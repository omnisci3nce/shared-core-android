# mobile-shared-core

Experiment with a C + SQLite configuration core that can be interfaced to via Android and iOS's native code calling functionality.

```
└── lib - third party dependencies
```

Compile sample
```
gcc lib/sqlite/sqlite3.c bin/create.c src/core.c -o create
```