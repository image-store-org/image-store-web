module.exports = {
	outputDir: '../../../target/classes/public',
	chainWebpack: config => {
		config.module
			.rule("vue")
			.use("vue-svg-inline-loader")
				.loader("vue-svg-inline-loader");
	}
}
