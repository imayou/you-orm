package com.ayou.orm.db;

import cn.hutool.core.collection.CollUtil;
import com.ayou.orm.db.ds.DSFactory;
import com.ayou.orm.db.ds.DataSourceWrapper;
import com.ayou.orm.db.ds.bee.BeeDSFactory;
import com.ayou.orm.db.ds.c3p0.C3p0DSFactory;
import com.ayou.orm.db.ds.dbcp.DbcpDSFactory;
import com.ayou.orm.db.ds.druid.DruidDSFactory;
import com.ayou.orm.db.ds.hikari.HikariDSFactory;
import com.ayou.orm.db.ds.pooled.PooledDSFactory;
import com.ayou.orm.db.ds.tomcat.TomcatDSFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据源单元测试
 *
 * @author Looly
 *
 */
public class DsTest {

	@Test
	public void defaultDsTest() throws SQLException {
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void hikariDsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new HikariDSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void druidDsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new DruidDSFactory());
		DataSource ds = DSFactory.get("test");

		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void tomcatDsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new TomcatDSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void beeCPDsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new BeeDSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void dbcpDsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new DbcpDSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void c3p0DsTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new C3p0DSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}

	@Test
	public void c3p0DsUserAndPassTest() {
		// https://gitee.com/dromara/hutool/issues/I4T7XZ
		DSFactory.setCurrentDSFactory(new C3p0DSFactory());
		ComboPooledDataSource ds = (ComboPooledDataSource) ((DataSourceWrapper) DSFactory.get("mysql")).getRaw();
		Assert.assertEquals("root", ds.getUser());
		Assert.assertEquals("123456", ds.getPassword());
	}

	@Test
	public void hutoolPoolTest() throws SQLException {
		DSFactory.setCurrentDSFactory(new PooledDSFactory());
		DataSource ds = DSFactory.get("test");
		Db db = Db.use(ds);
		List<Entity> all = db.findAll("user");
		Assert.assertTrue(CollUtil.isNotEmpty(all));
	}
}
