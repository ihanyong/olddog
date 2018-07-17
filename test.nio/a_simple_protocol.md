> 写一个简单的即时聊天系统
通过一个server, 两个client可以进行通信

V1.0 功能与设计
1. 用long型数字来标识用户
1. 用户使用client端, 通过server来交换信息
1. client 端应该先注册到server上, 建立链接
1. client 端发送一个消息, 另一个client端接收消息