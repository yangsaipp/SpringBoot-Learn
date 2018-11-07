/******************************************************************************
* Copyright (C) 2014 ShenZhen ComTop Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳康拓普开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package RegiestBeanByImport2;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author  杨赛
 * @since   jdk1.7
 * @version 2018年11月6日 杨赛
 */
@Configuration
@Import({MapperAutoConfiguredMyBatisRegistrar.class})
public class MapperAutoConfig {

}
