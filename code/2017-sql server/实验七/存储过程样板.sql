-- =======================================================================
--	CodeWorks系统
--	Projects表 相关存储过程脚本
--	作者：孙宜贵
-- =======================================================================

--------------------------------------------------------------------------
--查询全部记录 存储过程
--------------------------------------------------------------------------
IF EXISTS (SELECT * FROM dbo.sysobjects
		WHERE id = object_id(N'[dbo].[ProjectsSelectAll]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
DROP PROCEDURE [dbo].[ProjectsSelectAll]
GO
CREATE PROCEDURE ProjectsSelectAll
AS
	SET NOCOUNT ON

	 SELECT 
		XMBH, XMMC, XMXH, SSQY
		, QZSJ, SSJSLY, GDLY1, LY1KH
		, LY1XH, LY1HDMC, GDLY2, LY2XH
		, LY2HDMC, YJKFNRZY, YJKFFYYS, DNSJTR
		, GJCZBK, YJKFNX, QYZZTR, XMKFXS
		, XMYFXS, SBND, XMBCZT, PSFZ
		, SHJL, DHYY, SHRY, ZHPSJL
		, SFTGPS
	 FROM Projects

	SET NOCOUNT OFF
GO
--------------------------------------------------------------------------
-- 查询全部记录 储过程结束
--------------------------------------------------------------------------

--------------------------------------------------------------------------
--查询单个记录 存储过程
--------------------------------------------------------------------------
IF EXISTS (SELECT * FROM dbo.sysobjects
		WHERE id = object_id(N'[dbo].[ProjectsSelectOne]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
DROP PROCEDURE [dbo].[ProjectsSelectOne]
GO
CREATE PROCEDURE ProjectsSelectOne
	@XMBH	CHAR(16)--主键，若是复合主键，则需要多个参数。
AS
	SET NOCOUNT ON

	 SELECT 
		XMBH, XMMC, XMXH, SSQY
		, QZSJ, SSJSLY, GDLY1, LY1KH
		, LY1XH, LY1HDMC, GDLY2, LY2XH
		, LY2HDMC, YJKFNRZY, YJKFFYYS, DNSJTR
		, GJCZBK, YJKFNX, QYZZTR, XMKFXS
		, XMYFXS, SBND, XMBCZT, PSFZ
		, SHJL, DHYY, SHRY, ZHPSJL
		, SFTGPS
		 FROM Projects
		 WHERE 
			XMBH = @XMBH

	SET NOCOUNT OFF
GO
--------------------------------------------------------------------------
-- 查询单个记录 储过程结束
--------------------------------------------------------------------------

--------------------------------------------------------------------------
--删除单个记录 存储过程
--------------------------------------------------------------------------
IF EXISTS (SELECT * FROM dbo.sysobjects
		WHERE id = object_id(N'[dbo].[ProjectsDeleteOne]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
DROP PROCEDURE [dbo].[ProjectsDeleteOne]
GO
CREATE PROCEDURE ProjectsDeleteOne
	@XMBH	CHAR(16)--主键，若是复合主键，则需要多个参数。
AS
	SET NOCOUNT ON

	 DELETE Projects
		 WHERE 
			XMBH = @XMBH

	SET NOCOUNT OFF
GO
--------------------------------------------------------------------------
-- 删除单个记录 储过程结束
--------------------------------------------------------------------------

--------------------------------------------------------------------------
--修改单个记录 存储过程
--------------------------------------------------------------------------
IF EXISTS (SELECT * FROM dbo.sysobjects
		WHERE id = object_id(N'[dbo].[ProjectsUpdateOne]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
DROP PROCEDURE [dbo].[ProjectsUpdateOne]
GO
CREATE PROCEDURE ProjectsUpdateOne
	@XMBH	CHAR(16)
	,@XMMC	NVARCHAR(100)
	,@XMXH	SMALLINT
	,@SSQY	CHAR(9)

	,@QZSJ	VARCHAR(20)
	,@SSJSLY	CHAR(2) = NULL
	,@GDLY1	VARCHAR(20) = NULL
	,@LY1KH	VARCHAR(6) = NULL

	,@LY1XH	VARCHAR(6) = NULL
	,@LY1HDMC	NVARCHAR(200) = NULL
	,@GDLY2	VARCHAR(20) = NULL
	,@LY2XH	VARCHAR(6) = NULL

	,@LY2HDMC	NVARCHAR(200) = NULL
	,@YJKFNRZY	NVARCHAR(600) = NULL
	,@YJKFFYYS	DECIMAL(18,2) = NULL
	,@DNSJTR	DECIMAL(18,2) = NULL

	,@GJCZBK	DECIMAL(18,2) = NULL
	,@YJKFNX	NVARCHAR(14)
	,@QYZZTR	DECIMAL(18,2)
	,@XMKFXS	NVARCHAR(22)

	,@XMYFXS	NVARCHAR(12)
	,@SBND	CHAR(4)
	,@XMBCZT	NCHAR(4)
	,@PSFZ	CHAR(2) = NULL

	,@SHJL	NVARCHAR(6) = N'未审核'
	,@DHYY	NVARCHAR(100) = NULL
	,@SHRY	NVARCHAR(8) = NULL
	,@ZHPSJL	VARCHAR(10) = NULL

	,@SFTGPS	BIT = NULL

AS
	SET NOCOUNT ON

	 UPDATE Projects
		 SET
		XMMC = @XMMC
		, XMXH = @XMXH
		, SSQY = @SSQY
		, QZSJ = @QZSJ

		, SSJSLY = @SSJSLY
		, GDLY1 = @GDLY1
		, LY1KH = @LY1KH
		, LY1XH = @LY1XH

		, LY1HDMC = @LY1HDMC
		, GDLY2 = @GDLY2
		, LY2XH = @LY2XH
		, LY2HDMC = @LY2HDMC

		, YJKFNRZY = @YJKFNRZY
		, YJKFFYYS = @YJKFFYYS
		, DNSJTR = @DNSJTR
		, GJCZBK = @GJCZBK

		, YJKFNX = @YJKFNX
		, QYZZTR = @QYZZTR
		, XMKFXS = @XMKFXS
		, XMYFXS = @XMYFXS

		, SBND = @SBND
		, XMBCZT = @XMBCZT
		, PSFZ = @PSFZ
		, SHJL = @SHJL

		, DHYY = @DHYY
		, SHRY = @SHRY
		, ZHPSJL = @ZHPSJL
		, SFTGPS = @SFTGPS
		 WHERE 
			XMBH = @XMBH--主键，若是复合主键，则需要多个参数。

	SET NOCOUNT OFF
GO
--------------------------------------------------------------------------
-- 修改单个记录 储过程结束
--------------------------------------------------------------------------

--------------------------------------------------------------------------
--插入单个记录 存储过程
--------------------------------------------------------------------------
IF EXISTS (SELECT * FROM dbo.sysobjects
		WHERE id = object_id(N'[dbo].[ProjectsInsertOne]') AND OBJECTPROPERTY(id, N'IsProcedure') = 1)
DROP PROCEDURE [dbo].[ProjectsInsertOne]
GO
CREATE PROCEDURE ProjectsInsertOne
	@XMBH	CHAR(16)
	,@XMMC	NVARCHAR(100)
	,@XMXH	SMALLINT
	,@SSQY	CHAR(9)

	,@QZSJ	VARCHAR(20)
	,@SSJSLY	CHAR(2) = NULL
	,@GDLY1	VARCHAR(20) = NULL
	,@LY1KH	VARCHAR(6) = NULL

	,@LY1XH	VARCHAR(6) = NULL
	,@LY1HDMC	NVARCHAR(200) = NULL
	,@GDLY2	VARCHAR(20) = NULL
	,@LY2XH	VARCHAR(6) = NULL

	,@LY2HDMC	NVARCHAR(200) = NULL
	,@YJKFNRZY	NVARCHAR(600) = NULL
	,@YJKFFYYS	DECIMAL(18,2) = NULL
	,@DNSJTR	DECIMAL(18,2) = NULL

	,@GJCZBK	DECIMAL(18,2) = NULL
	,@YJKFNX	NVARCHAR(14)
	,@QYZZTR	DECIMAL(18,2)
	,@XMKFXS	NVARCHAR(22)

	,@XMYFXS	NVARCHAR(12)
	,@SBND	CHAR(4)
	,@XMBCZT	NCHAR(4)
	,@PSFZ	CHAR(2) = NULL

	,@SHJL	NVARCHAR(6) = N'未审核'
	,@DHYY	NVARCHAR(100) = NULL
	,@SHRY	NVARCHAR(8) = NULL
	,@ZHPSJL	VARCHAR(10) = NULL

	,@SFTGPS	BIT = NULL
AS
	SET NOCOUNT ON

	 INSERT Projects(
		XMBH, XMMC, XMXH, SSQY
		, QZSJ, SSJSLY, GDLY1, LY1KH
		, LY1XH, LY1HDMC, GDLY2, LY2XH
		, LY2HDMC, YJKFNRZY, YJKFFYYS, DNSJTR
		, GJCZBK, YJKFNX, QYZZTR, XMKFXS
		, XMYFXS, SBND, XMBCZT, PSFZ
		, SHJL, DHYY, SHRY, ZHPSJL
		, SFTGPS
		)
		VALUES(
		@XMBH, @XMMC, @XMXH, @SSQY
		, @QZSJ, @SSJSLY, @GDLY1, @LY1KH
		, @LY1XH, @LY1HDMC, @GDLY2, @LY2XH
		, @LY2HDMC, @YJKFNRZY, @YJKFFYYS, @DNSJTR
		, @GJCZBK, @YJKFNX, @QYZZTR, @XMKFXS
		, @XMYFXS, @SBND, @XMBCZT, @PSFZ
		, @SHJL, @DHYY, @SHRY, @ZHPSJL
		, @SFTGPS
		)

	SET NOCOUNT OFF
GO
--------------------------------------------------------------------------
-- 插入单个记录 储过程结束
--------------------------------------------------------------------------

-- =======================================================================
--	Projects表 相关存储过程脚本，结束。
-- =======================================================================
