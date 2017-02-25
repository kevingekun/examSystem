/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){
    Ext.QuickTips.init();
    //Ext.lib.Ajax.defaultPostHeader += ";charset=utf-8";
    /*function formatDate(value){
        return value ? value.dateFormat('M d, Y') : '';
    }*/
    // shorthand alias
    var fm = Ext.form;

    //checkbox选择模型
    var sm = new Ext.grid.CheckboxSelectionModel({ checkOnly: true });
    
    // custom column plugin example
    /*var checkColumn = new Ext.grid.CheckColumn({
       header: '选择',
       dataIndex: 'indoor',
       width: 50
    });*/

    // the column model has information about grid columns
    // dataIndex maps the column to the specific data field in
    // the data store (created below)
    var cm = new Ext.grid.ColumnModel([
       sm,
       {
           id: 'xwly',
           header: '行为领域',
           dataIndex: 'xwly',
           width: 320,
           // use shorthand alias defined above
           editor: new fm.TextField({
               allowBlank: false
           })
        },{
           header: '鉴定范围',
           dataIndex: 'jdfw',
           width: 320,
           editor: new fm.TextField({
               allowBlank: false
           })
        },{
           header: '鉴定点',
           dataIndex: 'jdd',
           width: 470,
           //renderer: 'usMoney',
           editor: new fm.TextField({
               allowBlank: false
           })
        },{
           header: '比例',
           dataIndex: 'bl',
           width: 70,
           align: 'right',
           //renderer: formatDate,
           editor: new fm.NumberField({
               allowBlank: false,
               allowNegative: false,
               maxValue: 100
           })
        }
       // checkColumn
    ]);

    // by default columns are sortable
    cm.defaultSortable = true;

    // create the Data Store
    var store = new Ext.data.Store({
        // load remote data using HTTP
        url: 'plants.xml',

        // specify a XmlReader (coincides with the XML format of the returned data)
        reader: new Ext.data.XmlReader(
            {
                // records will have a 'plant' tag
                record: 'plant'
            },
            // use an Array of field definition objects to implicitly create a Record constructor
            [
                // the 'name' below matches the tag name to read, except 'bl'
                // which is mapped to the tag 'availability'
                {name: 'xwly', type: 'string'},
                //{name: 'botanical', type: 'string'},
                {name: 'jdfw', type: 'string'},
                {name: 'jdd', type: 'string'},             
                // dates can be automatically converted by specifying dateFormat
                {name: 'bl', type: 'number'}
                //{name: 'indoor', type: 'bool'}
            ]
        ),

        sortInfo: {field:'xwly', direction:'ASC'}
    });

    // create the editor grid
    var grid = new Ext.grid.EditorGridPanel({
    	sm: sm,
        store: store,
        cm: cm,
        renderTo: 'editor-grid',
        width: 1136,
        height: 320,
        autoExpandColumn: 'xwly',
        title: '鉴定要素',
        frame: true,
       // plugins: checkColumn,
        clicksToEdit: 1,
        tbar: [{
            text: '添加',
            handler : function(){
                // access the Record constructor through the grid's store
                var Plant = grid.getStore().recordType;
                var p = new Plant({
                    xwly: '',
                    jdfw: '',
                    jdd: '',
                    bl: 5
                    //indoor: false
                });
                grid.stopEditing();
                store.insert(0, p);
                grid.startEditing(0, 0);
            }
        },{
        	text:"保存",
        	handler:function(){
        		var modified = store.modified;
        		updateData(modified);
        	}
        },{
        	text: "删除",
            handler: function() {
                var selModel = grid.getSelectionModel();
                if (selModel.hasSelection()) {
                    Ext.Msg.confirm("警告", "确定要删除吗？", function(button) {
                        if (button == "yes") {
                            var selections = selModel.getSelections();
                            Ext.each(selections, function(item) {
                                store.remove(item);
                            });
                        }
                    });
                }
                else {
                    Ext.Msg.alert("错误", "没有任何行被选中，无法进行删除操作！");
                }
            }
        }]
    });

    // trigger the data store load
    store.load();
    function updateData(modified) {
		if(checkGz()&&checkDj()){
			var gzmc = document.getElementById("gzmc").value;
			var dj = document.getElementById("dj").value;
			var id_job = document.getElementById("id_job").value;
		    var json = [];
		    var total = 0;
		    Ext.each(modified, function(item) {
		        json.push(item.data);
		        total = Number(total)+Number(item.data.bl);
		    });
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
		        Ext.Ajax.request({
		            url: "saveOrUpdateJdys.action",
		            params: { 
		            	data: Ext.util.JSON.encode(json),
		            	gzmc: gzmc,
		            	dj: dj,
		            	id_job: id_job},
		            method: "POST",
		            success: function(response) {
		                Ext.Msg.alert("信息", "新增成功！", function() { store.reload(); });
		            },
		            failure: function(response) {
		                Ext.Msg.alert("警告", "新增失败！");
		            }
		        });
		    }
		    else {
		        Ext.Msg.alert("警告", "没有需要新增的数据！");
		    }
		}
	}
});
