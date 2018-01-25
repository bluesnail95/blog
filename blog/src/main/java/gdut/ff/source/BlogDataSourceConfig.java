package gdut.ff.source;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "gdut.ff.mapper",sqlSessionTemplateRef = "blogSqlSessionTemplate")
public class BlogDataSourceConfig {
	
	@Bean(name = "blogDataSource")
	@ConfigurationProperties(prefix = "blog.datasource")
	@Primary
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name = "blogSqlSessionFactory")
	public SqlSessionFactory testSqlSessionFactory(@Qualifier("blogDataSource")DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/blog/user.xml"));
		return bean.getObject();
	}
	
	@Primary
	@Bean(name = "blogTransactionManager")
	public DataSourceTransactionManager testTransactionManager(@Qualifier("blogDataSource")DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Primary
	@Bean(name = "blogSqlSessionTemplate")
	public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("blogSqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
	    return new SqlSessionTemplate(sqlSessionFactory);
	}	

}
