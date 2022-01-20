const path = require('path')
const context = path.join(__dirname,'')
module.exports = {
    mode:'production',
    entry: {
        first:path.join(context,'/js/first.js')
    },
    output: {
        filename:'[name].bundle.js',
        path: path.resolve(__dirname,'/build/js'),
        chunkFilename:'[name].chunk.js'
    },
    devServer:{
        host:'0.0.0.0',
        port:3030,
        compress: true,
        hot : true,
        proxy:{
            '**' : 'http://localhost:8085'
        }
    },
    node:false,
    externals:{
        bufferutil:'bufferutil',
        "utf-8-validate":"utf-8-validate"
    },
    module:{
        rules:[
            {
              test:/\.m?js$/,
                exclude:/(node_modules|bower_components)/,
              use:{
                  loader: "babel-loader",
                  options: {
                      presets:["@babel/preset-env"],
                      plugins:["@babel/plugin-transform-object-assign",'@babel/plugin-transform-runtime'],
                  }
              }
            },
            {
                test:/\.js$/,
                exclude:/(node_modules|bower_components)/,
                loader:"webpack-remove-debug",
            }
        ]

    }
}