module.exports = {
	outputDir: '../../../target/classes/public',
	//svg
	chainWebpack: config => {
		config.module
			.rule("vue")
			.use("vue-svg-inline-loader")
			.loader("vue-svg-inline-loader");
	},
	//entry file
	pages: {
		index: {
			entry: 'src/scripts/ts/main.ts'
		}
	},
	//sass
	css: {
		loaderOptions: {
			scss: {
				additionalData: `@import "~@/css/scss/main.scss";`,
			}
		}
	}
}
