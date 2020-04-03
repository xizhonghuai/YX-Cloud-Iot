package com.shardingsphere;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
//import org.apache.shardingsphere.api.config.encryptor.EncryptRuleConfiguration;
//import org.apache.shardingsphere.core.yaml.config.encrypt.YamlRootEncryptRuleConfiguration;
//import org.apache.shardingsphere.core.yaml.engine.YamlEngine;
//import org.apache.shardingsphere.core.yaml.swapper.impl.EncryptRuleConfigurationYamlSwapper;
//import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.api.config.encrypt.EncryptRuleConfiguration;
import org.apache.shardingsphere.core.yaml.config.encrypt.YamlRootEncryptRuleConfiguration;
import org.apache.shardingsphere.core.yaml.engine.YamlEngine;
import org.apache.shardingsphere.core.yaml.swapper.impl.EncryptRuleConfigurationYamlSwapper;
import org.apache.shardingsphere.shardingjdbc.api.EncryptDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlEncryptDataSourceFactory;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName YXDataSourceFactory
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/3/27
 * @Version V1.0
 **/
public class YXDataSourceFactory extends PooledDataSourceFactory {

   /* @Override
    public DataSource getDataSource() {
        try {
            File yamlFile = Resources.getResourceAsFile("encryptRule.yml");
            YamlRootEncryptRuleConfiguration config = YamlEngine.unmarshal(yamlFile, YamlRootEncryptRuleConfiguration.class);
            EncryptRuleConfiguration ruleConfiguration =  new EncryptRuleConfigurationYamlSwapper().swap(config.getEncryptRule());
            try {
                return EncryptDataSourceFactory.createDataSource(super.getDataSource(), ruleConfiguration,new Properties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/


    @Override
    public DataSource getDataSource() {
        TransactionTypeHolder.set(TransactionType.BASE);

       /* DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, props);
*/

        try {
            File yamlFile = Resources.getResourceAsFile("encryptRule.yml");
            YamlRootEncryptRuleConfiguration config = YamlEngine.unmarshal(yamlFile, YamlRootEncryptRuleConfiguration.class);
            EncryptRuleConfiguration ruleConfiguration =  new EncryptRuleConfigurationYamlSwapper().swap(config.getEncryptRule());
            try {
                return EncryptDataSourceFactory.createDataSource(super.getDataSource(), ruleConfiguration,new Properties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
