/**
 * 应用服务层：
 * 
 * 面向系统用例,即服务，Service只负责协调并委派业务逻辑给领域对象进行处理，本身并不真正实现业务逻辑，绝大部分的业务逻辑都由领域对象承载和实现。
 * 
 * Service与其他的Service、领域对象和Repository 或 DAO进行交互。
 * 
 * 传统方式下Service主要负责了业务逻辑的实现，领域对象只有getter和setter方法。
 */
/**
 * @author yangsai
 *
 */
package com.example.service;