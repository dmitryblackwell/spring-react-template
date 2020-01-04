const path = require('path');

module.exports = {
  context: __dirname,
  entry: path.join(__dirname, 'src', 'main', 'webapp', 'index.js'),
  output:{
    path: path.join(__dirname, 'src', 'main', 'resources', 'static', 'build'),
    filename: "bundle.js"
  },
  devServer: {
    contentBase: './target/webpack-dev-server',
    compress: true,
    port: 8000,
    allowedHosts: [
      'localhost:9000'
    ]
  },
  resolve: {
    modules: [
      path.join(__dirname, 'src', 'main', 'resources', 'static', 'build'),
      path.join(__dirname, 'node_modules'),
    ],
  },
  module: {
    rules: [
      {
        loader: 'babel-loader',
        exclude: /node_modules/,
        test: /\.js$/,
        options: {
          presets: [
            ["@babel/preset-env", {modules: false, targets: {browsers: ['last 2 versions']}}],
            '@babel/preset-react'
          ],
          cacheDirectory: true,
          plugins: [
            ['import', { libraryName: "antd", style: true }],
            'transform-strict-mode',
            'transform-object-rest-spread',
            '@babel/plugin-proposal-class-properties'
          ]
        },
      },
      {
        test: /\.css$/i,
        use: [{loader: 'style-loader'}, {
          loader: 'css-loader',
          options: {
            importLoaders: 1,
            modules: true
          }
        }],
      },
      {
        test: /\.(jpg|jpeg|png|gif|mp3)$/,
        exclude: /node_modules/,
        loader: "file-loader?name=/assets/[name].[ext]"
      },
      {
        test: /\.svg$/,
        use: ['@svgr/webpack'],
      },
      {
        test: /\.less$/,
        use: [
          {loader: "style-loader"},
          {loader: "css-loader"},
          {loader: "less-loader",
            options: {
              root: path.resolve(__dirname, './'),
              javascriptEnabled: true
            }
          }
        ]
      }
    ]
  },
};
