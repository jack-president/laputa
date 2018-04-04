[
  {
    "sysOperation": {
      "cname": "${sysEntity.cname}",
      "code": "OPERATION_${project?upper_case}_${className?upper_case}",
      "prefixUrl": "/${project}/${className?lower_case}/*",
      "descript": "${sysEntity.cname}操作"
    },
    "childSysOperation": [
      {
        "cname": "查看列表",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_LIST",
        "prefixUrl": "/${project}/${className?lower_case}/list",
        "descript": ""
      },
      {
        "cname": "全量读取",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_READEAGER",
        "prefixUrl": "/${project}/${className?lower_case}/readEager",
        "descript": ""
      },
      {
        "cname": "数据源读取",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_READDATASOURCE",
        "prefixUrl": "/${project}/${className?lower_case}/readDataSource",
        "descript": ""
      },
      {
        "cname": "新增",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_CREATE",
        "prefixUrl": "/${project}/${className?lower_case}/create",
        "descript": ""
      },
      {
        "cname": "删除",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_DESTORY",
        "prefixUrl": "/${project}/${className?lower_case}/destory",
        "descript": ""
      },
      {
        "cname": "更新",
        "code": "OPERATION_${project?upper_case}_${className?upper_case}_UPDATE",
        "prefixUrl": "/${project}/${className?lower_case}/update",
        "descript": ""
      }
    ]
  }
]