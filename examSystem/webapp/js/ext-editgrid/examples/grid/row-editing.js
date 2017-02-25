Ext.require([
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*',
    'Ext.form.*'
]);
//var gzid = '6-04-02-05',dj='5';
Ext.onReady(function(){
    // Define our data model
    Ext.define('Jdys', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'id', type: 'int'},
			{name: 'xwly', type: 'string'},
			{name: 'jdfw', type: 'string'},
			{name: 'jdd', type: 'string'},
			{name: 'bl',  type: 'int'}
        ]
    });
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', { // 行编辑模式
        clicksToMoveEditor : 2,   //双击编辑  整行修改
        autoCancel : false,
        saveBtnText:'确定',
        cancelBtnText:'取消',
        errorsText:'错误',
        dirtyText:'你要确认或取消更改'
    });

    // create the Data Store
    var store = Ext.create('Ext.data.Store', {
        // destroy the store if the grid is destroyed
        /*autoDestroy: true,
        model: 'Employee',
        proxy: {
            type: 'memory'
        },
        data: data,
        sorters: [{
            property: 'start',
            direction: 'ASC'
        }]*/
    	model: 'Jdys',
        proxy: {
            type: "ajax",
            url: "getJdys.action"
            /*reader: {
                type: 'json',
                model: 'jdys'
                //root: 'json'
                //totalProperty: 'total'
                //idProperty: 'ID'
            }*/
        }
        /*sorters: [{
            property: 'xwly',
            direction: 'ASC'
        }],
        autoLoad: true*/
    });
    //store.load();
    var gzdj;
    if(dj=='一级'){
		gzdj='1';
	}else if(dj=='二级'){
		gzdj='2';
	}else if(dj=='三级'){
		gzdj='3';
	}else if(dj=='四级'){
		gzdj='4';
	}else if(dj=='五级'){
		gzdj='5';
	}else if(dj=='专项'){
		gzdj='6';
	}
    /*if(state==1){
    	store.load({ params: { gzid: gzid,dj: gzdj} });
    }else{
    	store.load();
    }*/
    store.load({ params: { gzid: gzid,dj: gzdj,companyBriefCode: cpyBC} });

   /* var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false
    });*/
    // create the grid and specify what field you want
    // to use for the editor at each column.
    var checkBox = Ext.create('Ext.selection.CheckboxModel');
    var title = '鉴定要素';
    if(gzmc=='null'){
    }else{
    	title = title+' > '+cpyFS+gzmc+' > '+dj;
    }
    var grid = Ext.create('Ext.grid.Panel', {
        store: store,
        selModel:checkBox,
        disableSelection: false,//表示可以选择行 
        columnLines: true,
        loadMask: true,
        columns: [
		{
		    header : 'id',  
		    dataIndex : 'id',  
		    hidden:true
		}, {
            header: '行为领域',
            dataIndex: 'xwly',
            flex: 1,
            editor: {
            	xtype: 'textfield',
                allowBlank: false
            }
        }, {
            header: '鉴定范围',
            dataIndex: 'jdfw',
            //width: 160,
            flex: 1,
            editor: {
            	xtype: 'textfield',
            	allowBlank: false
            }
        }, {
            header: '鉴定点',
            dataIndex: 'jdd',
            //width: 160,
            flex: 1,
            editor: {
            	xtype: 'textfield',
                allowBlank: false
            }
        }, {
            header: '比例',
            dataIndex: 'bl',
            width: 90,
            editor: {
                xtype: 'numberfield',
                allowBlank: false,
                value:5,
                minValue: 0,
                maxValue: 100
            }
        }
        ],
        renderTo: 'editor-grid',
        width: 1122,
        height: 310,
        title: title,//'鉴定要素 > '+gzmc+' > '+dj
        frame: true,
        tbar: [{
        	itemId: 'add',
            text: '添加',
            //iconCls: 'employee-add',
            handler : function() {
                rowEditing.cancelEdit();
                // Create a model instance
                var r = Ext.create('Jdys', {
                	id:'',
                    xwly: ' ',
                    jdfw: ' ',
                    jdd: ' ',
                    bl: 5
                });

                store.insert(0, r);
                rowEditing.startEdit(0, 0);
            }
        },{
        	text:"保存",
        	handler:function(){
        		//var json = [];
        		if(gzmc=='null'||dj=='0'){
        			alert("工种或等级有误！");
        			return;
        		}
        		if(tkCat=='1'||tkCat=='2'||tkCat=='3'||tkCat=='5'){
    			}else{
    				if(cpyFS==''){
    					alert("请填写题库简称！");
    					$("#dj").val("0");
    					return ;
    				}
    				if(cpyBC==''){
    					alert("请填写题库简码！");
    					$("#dj").val("0");
    					return ;
    				}
    			}
    		    var total = 0;
        		store.each(function(record,i){
        		   // json.push(record.data);
    		        total = Number(total)+Number(record.data.bl);
        		});
        		//alert(total);
        		if(total<100){
    		    	var value = 100-Number(total);
    		    	alert("还差"+value+"分");
    		    	return;
    		    }
    		    if(total>100){
    		    	var value = Number(total)-100;
    		    	alert("超出"+value+"分");
    		    	return;
    		    }
    		    if (total==100) {//json.length > 0
    		    	var mjson = [];//存放修改的数据
    		    	var djson = [];//存放删除的数据id
    		    	var mjsons = store.getModifiedRecords();
    		    	
    		    	Ext.each(mjsons, function(item) {
    		    		mjson.push(item.data);
                    });
    		    	var djsons = store.getRemovedRecords();
        		    if(state==1){//此工种等级下鉴定要素已经存在
        		    	Ext.each(djsons, function(item) {
                            djson.push(item.data.id);
                        });
        		    }
    		    	if(mjson.length>0||djson.length>0){
            		    //alert(mjson[0].data)
        		        Ext.Ajax.request({
        		            url: "saveOrUpdateJdys.action",
        		            params: {
        		            	data: Ext.JSON.encode(mjson),//Ext.JSON.encode(mjson)
        		            	gzmc: gzmc,
        		            	dj: dj,
        		            	id_job: gzid,
        		            	data_d: djson,
        		            	state: state,
        		            	tkCategory: tkCat,
        		            	companyForShort: cpyFS,
        		            	companyBriefCode: cpyBC},
        		            method: "POST",
        		            success: function(response) {
        		                Ext.Msg.alert("信息", "保存成功！", function() { state=1;store.reload(); });
        		            },
        		            failure: function(response) {
        		                Ext.Msg.alert("警告", "保存失败！");
        		            }
        		        });
    		    	}else{
    		    		Ext.Msg.alert("警告", "没有修改任何数据！");
    		    	}
    		    }
        	}
        },{
            //itemId: 'remove',
            text: '移除',
            //iconCls: 'employee-remove',
            handler: function() {
            	var selModel = grid.getSelectionModel();
                if (selModel.hasSelection()) {
                	Ext.MessageBox.confirm("警告", "确定要删除吗？", function(button) {
                        if (button == "yes") {
                            var selections = selModel.getSelection();
                            Ext.each(selections, function(item) {
                                store.remove(item);
                            });
                        }
                    });
                }else {
                    Ext.MessageBox.alert("错误", "没有任何行被选中，无法进行删除操作！");
                }
            }
        }],
        plugins: [rowEditing]
        /*listeners: {
            'selectionchange': function(view, records) {
                grid.down('#remove').setDisabled(!records.length);
            }
        }*/
    });
});
