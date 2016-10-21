var React = require('react-native');
var {
    NativeModules
} = React;
var barcodeBuilder = NativeModules.BarcodeBuilder;

function testBase64(){
    barcodeBuilder.buildBase64("abc",100).then((result)=>{alert(result);},(error)=>{alert(error);});
}
function testFile(){
    var path = '/sdcard/test.png';
    barcodeBuilder.buildFile(path,"abc",100).then((result)=>{alert(result);},(error)=>{alert(error);});
}
barcodeBuilder.test = {
base64: testBase64,
file:testFile
}
module.exports=barcodeBuilder;
