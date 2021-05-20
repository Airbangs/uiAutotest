===========================

###########环境依赖
JDK  
***
###########部署步骤
1. 添加系统环境变量  
***
###########目录结构描述
├── Readme.md                   // help  
├── driver                         // 驱动  
├── src                      //  
│   └── main    
│       └── java  
│   └── test  
├── testdata                      // 执行用例中所需测试文件，如上传附件等
***
###########V1.0.0 版本内容更新
1. demo
***
###########To-do List
-[ ] 整理demo框架
  -[ ] 写READM.md

***
```
gantt
dateFormat YYYY-MM-DD
title 计划表
section 从0〜1
按以往经验demo:2020-5-20, 1d
section 规范化
学习优秀框架:2020-6-10, 20d
section 完善
引用优秀点:2020-6-30, 20d
```

***
**页面元素调用常用方法说明**：
- element.clear()                 # 清除文本  
- element.send_keys(value)        # 输入文字或键盘按键（需导入Keys模块）  
- element.click()                 # 单击元素  
- element.get_attribute(name)     # 获得属性值  
- element.is_displayed()          # 返回元素结果是否可见（True 或 False）  
- element.is_selected()           # 返回元素结果是否被选中（True 或 False）  
- element.find_element*()         # 定位元素，用于二次定位  
***
>总结催动完善