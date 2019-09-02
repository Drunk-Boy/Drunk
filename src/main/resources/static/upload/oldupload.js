<script type="text/javascript" th:inline="javascript">
// var basePath = /*[[${#httpServletRequest.getScheme() + "://" + #httpServletRequest.getServerName() + ":" + #httpServletRequest.getServerPort() + #httpServletRequest.getContextPath()}]]*/;

var filepath = $("#txt_file").val();
//文件上传略缩图的下载按钮
var btns = '<button type="button" class="kv-cust-btn btn btn-kv btn-secondary" title="下载">' + '<i class="glyphicon glyphicon-edit"></i>' + '</button>';
$(function () {
    $("#file_input").click(function () {

    })
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("txt_file", basePath+"/elder/input");
});
//初始化fileinput
var FileInput = function () {
    var oFile = new Object();

    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);

        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //发送请求的地址
            allowedFileExtensions: ['jpg', 'gif', 'png','pdf','doc','rar'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            maxfileSize: 0,             //文件的最大
            showRemove:false,  //不显示移除按钮
            previewFileType:['image','html','flash','text'],
            //dropZoneEnabled: false,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            preferIconicPreview: true, // 开启用图标替换预览效果
            // maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            otherActionButtons:btns,            //略缩图上加按钮
            //下面几个就是初始化预览图片的配置      
            initialPreviewAsData: true,
            initialPreviewFileType: 'image',
            initialPreview:path , //要显示的图片的路径  
            initialPreviewConfig:con,
            previewFileIconSettings: { // configure your icon file extensions
                'doc': '<i class="fa fa-file-word-o text-primary"></i>',
                'xls': '<i class="fa fa-file-excel-o text-success"></i>',
                'ppt': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
                'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
                'txt': '<i class="fa fa-file-text-o text-info"></i>',
                'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
                'htm': '<i class="fa fa-file-code-o text-info"></i>',
                'mov': '<i class="fa fa-file-movie-o text-warning"></i>',
                'mp3': '<i class="fa fa-file-audio-o text-warning"></i>'
            },
            previewFileExtSettings: {
                'doc': function (ext) {
                    return ext.match(/(doc|docx)$/i);
                },
                'xls': function (ext) {
                    return ext.match(/(xls|xlsx)$/i);
                },
                'ppt': function (ext) {
                    return ext.match(/(ppt|pptx)$/i);
                },
                'zip': function (ext) {
                    return ext.match(/(zip|rar|tar|gzip|gz|7z)$/i);
                },
                'htm': function (ext) {
                    return ext.match(/(htm|html)$/i);
                },
                'mov': function (ext) {
                    return ext.match(/(avi|mpg|mkv|mov|mp4|3gp|webm|wmv)$/i);
                },
                'mp3': function (ext) {
                    return ext.match(/(mp3|wav)$/i);
                },
                'txt': function (ext) {
                    return ext.match(/(txt|ini|csv|java|php|js|css)$/i);
                }
            },
            layoutTemplates: { // 预览图片按钮控制，这里屏蔽预览按钮
                actionZoom:''
            },
            uploadExtraData:function (previewId, index) {           //传参
                var data = {
                    "filepath":filepath,
                    "data": "mydata"      //此处自定义传参
                };
                return data;
            }
        });


        // //导入文件上传完成之后的事件
        $("#txt_file").on("fileuploaded", function (event, data, previewId, index) {
            // 将fileId放在previewId为id的div中
            $('#'+previewId).attr('fileid',data.response.fileid);
            // 将fileid放入form表单
            var fileId ='<input id="fileId" name="fileId" type="hidden" value="'+data.response.fileid+'">';
            $("#addForm").append(fileId)
        }).on("filesuccessremove",function (event, previewId, extra) {
            // 点击移除触发的事件
            // 取出previewId为id的div中的属性 后删除
            var id = $('#'+previewId).attr("fileid");
            delete(($('#'+previewId).attr('fileid')));
            // 将fileid从表单中移除
            $('#fileId').remove();
            $.ajax({
                type: "POST",
                url: basePath+"/elder/inputDelete",
                data: "id="+id,
                success: function(msg){
                    alert( "删除成功");
                }
            });
        });

        // 异步上传错误结果处理
        $("#txt_file").on("fileerror", function(event, data, msg) {
            alert("上传失败")
        });
    }
    return oFile;
};



</script>