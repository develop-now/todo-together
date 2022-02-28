const withPlugins = require('next-compose-plugins');
const CompressionPlugin = require('compression-webpack-plugin');
const withBundleAnalyzer = require('@next/bundle-analyzer')({
	enabled: process.env.ANALYZE === 'true',
});

const nextConfig = {
	reactStrictMode: true,
	images: {
		loader: 'akamai',
		path: '',
	},
	async headers() {
		return [
			{
				source: '/(.*)',
				headers: [
					{
						key: 'Strict-Transport-Security',
						value: 'max-age=63072000; includeSubDomains; preload',
					},
					{
						key: 'X-Frame-Options',
						value: 'sameorigin',
					},
					{
						key: 'X-Content-Type-Options',
						value: 'nosniff',
					},
					{
						key: 'Referrer-Policy',
						value: 'same-origin',
					},
					{
						key: 'X-XSS-Protection',
						value: '1; mode=block',
					},
				],
			},
		];
	},
};

const bundleAnalyzer = withBundleAnalyzer({
	compress: true,
	webpack(config) {
		const prod = process.env.NEXT_PUBLIC_MODE === 'prod';
		const plugins = [...config.plugins];
		if (prod) {
			plugins.push(new CompressionPlugin());
		}
		return {
			...config,
			mode: prod ? 'production' : 'development',
			plugins,
		};
	},
});

module.exports = withPlugins([bundleAnalyzer], nextConfig);
