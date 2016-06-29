package com.bms.system.extapp;

import com.bms.system.extapp.bean.DbSchema;
import com.bms.system.extapp.bean.Table;


public class CreateBean {
	
	public static void createBean(DbSchema bean, String filePath){
		String path = filePath  +"model/"
                + bean.getClassName().toLowerCase()+"/"+bean.getClassName().toLowerCase()+"Model";
        path = path.replaceAll("\\.", "/");
        AotuExtJs.createFile(path + ".js",
                getHolderBeanContent(bean));
	}
	
	public static void createStore(DbSchema bean, String filePath){
		String path = filePath  +"store/"
                + bean.getClassName().toLowerCase()+"/"+bean.getClassName()+"Store";
        path = path.replaceAll("\\.", "/");
        AotuExtJs.createFile(path + ".js",
        		getStoreContent(bean));
	}
	
	public static void createController(DbSchema bean, String filePath){
		String path = filePath  +"controller/"
                + bean.getClassName().toLowerCase()+"/"+bean.getClassName()+"Controller";
        path = path.replaceAll("\\.", "/");
        AotuExtJs.createFile(path + ".js",
        		createController(bean));
	}
	
	public static void createView(DbSchema bean, String filePath){
		String path = filePath  +"view/"
				+ bean.getClassName().toLowerCase()+"/"+"%s";
        path = path.replaceAll("\\.", "/");
        
        AotuExtJs.createFile(String.format(path,bean.getClassName()+"Add") + ".js",
        		createViewAdd(bean));
        AotuExtJs.createFile(String.format(path,bean.getClassName()+"Update") + ".js",
        		createViewUpdate(bean));
        AotuExtJs.createFile(String.format(path,bean.getClassName()+"List") + ".js",
        		createViewList(bean));
	}
	
	public static String getStoreContent(DbSchema bean){
		StringBuffer buf = new StringBuffer();
		
		buf.append("Ext.define(\"GD.store.").append(bean.getClassName().toLowerCase()).append(".").append(bean.getClassName()).append("Store\",{");
		buf.append(System.getProperty("line.separator"));
		buf.append("	extend: 'Ext.data.Store',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	model: 'GD.model.").append(bean.getClassName().toLowerCase()).append(".").append(bean.getClassName().toLowerCase()).append("Model',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	pageSize:limit,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	defaultRootId: 'root',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	proxy: {");
		buf.append(System.getProperty("line.separator"));
		buf.append("		type:'ajax',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		url: baseUrl + '/").append(getClassName(bean.getClassName())).append("/ts_getGrid.l',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		reader:{");
		buf.append(System.getProperty("line.separator"));
		buf.append("			 type: 'json',");
		buf.append(System.getProperty("line.separator"));
		buf.append("			 root: 'result',");
		buf.append(System.getProperty("line.separator"));
		buf.append("             totalProperty: 'totalCount'");
		buf.append(System.getProperty("line.separator"));
		buf.append("		 }");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},");
		buf.append(System.getProperty("line.separator"));
		buf.append("	autoLoad: false");
		buf.append(System.getProperty("line.separator"));
		buf.append("});");
		
		return buf.toString(); 
	}
	
	private static String getHolderBeanContent(DbSchema bean){
		StringBuffer buf = new StringBuffer();
		buf.append("Ext.define('GD.model."+bean.getClassName().toLowerCase()
				+"."+bean.getClassName().toLowerCase()+"Model',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("extend: 'Ext.data.Model',");
		buf.append(System.getProperty("line.separator"));
		buf.append("fields: [");
		buf.append(System.getProperty("line.separator"));
		
		for(int i = 0; i < bean.getList().size(); i++){
			Table table = bean.getList().get(i);
			if (i==(bean.getList().size()-1)){
				buf.append("			{name: '"+table.getAttrName()+"',  				type: 'string'}");
            }else{
            	buf.append("			{name: '"+table.getAttrName()+"',  					type: 'string'},");
            	buf.append(System.getProperty("line.separator"));
            }
		}
		
		buf.append(System.getProperty("line.separator"));
		buf.append("]");
		buf.append(System.getProperty("line.separator"));
		buf.append("});");
		
		return buf.toString();
	}
	
	public static String createViewAdd(DbSchema bean){
		String toName = bean.getClassName().toLowerCase();
		StringBuffer buf = new StringBuffer();
		buf.append("Ext.define('GD.view."+bean.getClassName().toLowerCase()+"."+bean.getClassName()+"Add',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("	extend:'Ext.form.Panel',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	alias:'widget."+bean.getClassName().toLowerCase()+"Add',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	labelWidth : 70,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	labelAlign : 'right',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	url : baseUrl + '/"+getClassName(bean.getClassName())+"/ts_add.l',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	border : false,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	bodyStyle : 'padding:5px 5px 0',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	defaultType : 'textfield',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	buttonAlign: 'center',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	defaults : {");
		buf.append(System.getProperty("line.separator"));
		buf.append("		width : 280,");
		buf.append(System.getProperty("line.separator"));
		buf.append("		labelAlign: 'right'");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},");
		buf.append(System.getProperty("line.separator"));
		buf.append("	items:[");
		buf.append(System.getProperty("line.separator"));
		
		for(int i=0;i<bean.getList().size();i++){
			
			Table table = bean.getList().get(i);
			
			if (i==(bean.getList().size()-1)){				
				buf.append("		     {");
				buf.append(System.getProperty("line.separator"));
				buf.append("		fieldLabel : '"+table.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		name : '"+table.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		allowBlank : false");
				buf.append(System.getProperty("line.separator"));
				buf.append("	}");
			}else{
				buf.append("		     {");
				buf.append(System.getProperty("line.separator"));
				buf.append("		fieldLabel : '"+table.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		name : '"+table.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		allowBlank : false");
				buf.append(System.getProperty("line.separator"));
				buf.append("	},");
			}
			
		}
		
		buf.append("		],");
		buf.append(System.getProperty("line.separator"));
		buf.append("	buttons : [{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		text:'添加',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		formBind:true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("		handler:function(btn) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("			var win = btn.up('window');");
		buf.append(System.getProperty("line.separator"));
		buf.append("	    	var store = btn.up('"+bean.getClassName().toLowerCase()+"Add').store;");
		buf.append(System.getProperty("line.separator"));
		buf.append("			var frm = this.ownerCt.ownerCt.form;");
		buf.append(System.getProperty("line.separator"));
		buf.append("			if (frm.isValid()) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("				btn.disable();");
		buf.append(System.getProperty("line.separator"));
		buf.append("				frm.submit({");
		buf.append(System.getProperty("line.separator"));
		buf.append("					waitTitle : '请稍候',");
		buf.append(System.getProperty("line.separator"));
		buf.append("					waitMsg : '正在提交表单数据,请稍候...',");
		buf.append("					submitEmptyText: false,");
		buf.append(System.getProperty("line.separator"));
		buf.append("					success : function(form, action) {	");	
		buf.append(System.getProperty("line.separator"));
		buf.append("						MsgTip.msg(\"消息提示ʾ\",action.result.msg);");
		buf.append(System.getProperty("line.separator"));
		buf.append("						btn.enable();");	
		buf.append(System.getProperty("line.separator"));
		buf.append("						store.load();");
		buf.append(System.getProperty("line.separator"));
		buf.append("						window.parent.Ext.getCmp('add"+toName+"').close();");
		buf.append(System.getProperty("line.separator"));
		buf.append("					},");
		buf.append(System.getProperty("line.separator"));
		buf.append("					failure : function(form, action) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("						Ext.Msg.show({");
		buf.append(System.getProperty("line.separator"));
		buf.append("							title : '错误提示ʾ',");
		buf.append(System.getProperty("line.separator"));
		buf.append("							msg : action.result.msg,");
		buf.append(System.getProperty("line.separator"));
		buf.append("							buttons : Ext.Msg.OK,");
		buf.append(System.getProperty("line.separator"));
		buf.append("							fn : function() {");
		buf.append(System.getProperty("line.separator"));
		buf.append("								btn.enable();");
		buf.append(System.getProperty("line.separator"));
		buf.append("							},");
		buf.append(System.getProperty("line.separator"));
		buf.append("							icon : Ext.Msg.ERROR");
		buf.append(System.getProperty("line.separator"));
		buf.append("						});");
		buf.append(System.getProperty("line.separator"));
		buf.append("					}");
		buf.append(System.getProperty("line.separator"));
		buf.append("				});");
		buf.append(System.getProperty("line.separator"));
		buf.append("			}");
		buf.append(System.getProperty("line.separator"));
		buf.append("		}");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		text : '重置',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		handler : function() {");
		buf.append(System.getProperty("line.separator"));
		buf.append("			this.ownerCt.ownerCt.form.reset();");
		buf.append(System.getProperty("line.separator"));
		buf.append("		}");
		buf.append(System.getProperty("line.separator"));
		buf.append("	}]");
		buf.append(System.getProperty("line.separator"));
		buf.append("});");
		
		return buf.toString();
	}
	
	public static String createViewUpdate(DbSchema bean){
		String toName = bean.getClassName().toLowerCase();
		String upName = bean.getClassName();
		String spName = getClassName(upName);
		StringBuffer buf = new StringBuffer();
		buf.append("Ext.define('GD.view."+toName+"."+upName+"Update',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("	extend:'Ext.form.Panel',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	alias:'widget."+toName+"Update',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	labelWidth : 70,");
		buf.append(System.getProperty("line.separator"));
		buf.append(System.getProperty("line.separator"));
		buf.append("	url : baseUrl + '/"+spName+"/ts_update.l',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	border : false,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	bodyStyle : 'padding:5px 5px 0',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	defaultType : 'textfield',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	buttonAlign: 'center',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	defaults : {");
		buf.append(System.getProperty("line.separator"));
		buf.append("		width : 280,");
		buf.append(System.getProperty("line.separator"));
		buf.append("		labelAlign : 'right'");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},");
		buf.append(System.getProperty("line.separator"));
		buf.append("	items:[");
		buf.append(System.getProperty("line.separator"));
		
		Table t = null;
		for(int i=0;i<bean.getList().size();i++){
			t=bean.getList().get(i);
			if (i==(bean.getList().size()-1)){				
				buf.append("		     {");
				buf.append(System.getProperty("line.separator"));
				buf.append("		fieldLabel : '"+t.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		name : '"+t.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		allowBlank : false");
				buf.append(System.getProperty("line.separator"));
				buf.append("	}");
			}else{
				buf.append("		     {");
				buf.append(System.getProperty("line.separator"));
				buf.append("		fieldLabel : '"+t.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		name : '"+t.getAttrName()+"',");
				buf.append(System.getProperty("line.separator"));
				buf.append("		allowBlank : false");
				buf.append(System.getProperty("line.separator"));
				buf.append("	},");
			}
			buf.append(System.getProperty("line.separator"));
		}
		
		buf.append("	 ],");
		buf.append(System.getProperty("line.separator"));
		buf.append("	buttons : [{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		text:'修改',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		formBind:true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("		handler:function(btn) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("			var win = btn.up('window');");
		buf.append(System.getProperty("line.separator"));
		buf.append("	    	var store = btn.up('"+toName+"Update').store;");
		buf.append(System.getProperty("line.separator"));
		buf.append("			var frm = this.ownerCt.ownerCt.form;");
		buf.append(System.getProperty("line.separator"));
		buf.append("			if (frm.isValid()) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("				btn.disable(); ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				frm.submit({");
		buf.append(System.getProperty("line.separator"));
		buf.append("					waitTitle : '请稍候',");
		buf.append(System.getProperty("line.separator"));
		buf.append("					waitMsg : '正在提交表单数据,请稍候...',");
		buf.append("					submitEmptyText: false,");
		buf.append(System.getProperty("line.separator"));
		buf.append("					success : function(form, action) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("						MsgTip.msg('消息提示ʾ',action.result.msg);");
		buf.append(System.getProperty("line.separator"));
		buf.append("						btn.enable();");
		buf.append(System.getProperty("line.separator"));
		buf.append("						store.load({limit:limit});");
		buf.append(System.getProperty("line.separator"));
		buf.append("						window.parent.Ext.getCmp('update"+toName+"').close();");
		buf.append(System.getProperty("line.separator"));
		buf.append("					},");
		buf.append(System.getProperty("line.separator"));
		buf.append("					failure : function(form, action) {");
		buf.append(System.getProperty("line.separator"));
		buf.append("						Ext.Msg.show({");
		buf.append(System.getProperty("line.separator"));
		buf.append("							title : '错误提示ʾ',");
		buf.append(System.getProperty("line.separator"));
		buf.append("							msg : action.result.msg,");
		buf.append(System.getProperty("line.separator"));
		buf.append("							buttons : Ext.Msg.OK,");
		buf.append(System.getProperty("line.separator"));
		buf.append("							fn : function() {");
		buf.append(System.getProperty("line.separator"));
		buf.append("								btn.enable();");
		buf.append(System.getProperty("line.separator"));
		buf.append("							},");
		buf.append(System.getProperty("line.separator"));
		buf.append("							icon : Ext.Msg.ERROR");
		buf.append(System.getProperty("line.separator"));
		buf.append("						});");
		buf.append(System.getProperty("line.separator"));
		buf.append("					}");
		buf.append(System.getProperty("line.separator"));
		buf.append("				});");
		buf.append(System.getProperty("line.separator"));
		buf.append("			}");
		buf.append(System.getProperty("line.separator"));
		buf.append("		}");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		text : '重置',");
		buf.append(System.getProperty("line.separator"));
		buf.append("		handler : function() {");
		buf.append(System.getProperty("line.separator"));
		buf.append("			this.ownerCt.ownerCt.form.reset();");
		buf.append(System.getProperty("line.separator"));
		buf.append("		}");
		buf.append(System.getProperty("line.separator"));
		buf.append("	}],");
		buf.append(System.getProperty("line.separator"));
		buf.append("	listeners:{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		beforerender:function(obj, eOpts){");
		buf.append(System.getProperty("line.separator"));
		
		for(int i=0;i<bean.getList().size();i++){
			t=bean.getList().get(i);
			buf.append("			obj.getForm().findField('"+t.getAttrName()+"').setValue(obj.model.get('"+t.getAttrName()+"'));  ");
			buf.append(System.getProperty("line.separator"));
		}
		
		buf.append("		}");
		buf.append(System.getProperty("line.separator"));
		buf.append("	}");
		buf.append(System.getProperty("line.separator"));
		buf.append("});");
		return buf.toString();
	}
	
	public static String createViewList(DbSchema bean){
		String toName = bean.getClassName().toLowerCase();
		String upName = bean.getClassName();
		String spName = getClassName(upName);
		StringBuffer buf = new StringBuffer();
		buf.append("Ext.define('GD.view."+toName+"."+upName+"List',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("	extend:'Ext.grid.Panel',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	alias:'widget."+toName+"List',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	store:'"+toName+"."+upName+"Store',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	selModel:{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		selType:'checkboxmodel'");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},");
		buf.append(System.getProperty("line.separator"));
		buf.append("	viewConfig:{");
		buf.append(System.getProperty("line.separator"));
		buf.append("		 enableTextSelection: true");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},");
		buf.append(System.getProperty("line.separator"));
		buf.append("	columnLines:true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	multiSelect:true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("	columns:[");
		buf.append(System.getProperty("line.separator"));
		buf.append("         {xtype:'rownumberer',align:'center'},");
		buf.append(System.getProperty("line.separator"));
		
		Table t = null;
		for(int i=0;i<bean.getList().size();i++){
			t=bean.getList().get(i);
			if(i==(bean.getList().size()-1)){
				buf.append("    	 {text:'"+t.getAttrName()+"',		dataIndex:'"+getClassName(t.getAttrName())+"',		align:'center',		flex:1}");
			}else{
				buf.append("    	 {text:'"+t.getAttrName()+"',		dataIndex:'"+getClassName(t.getAttrName())+"',	align:'center'},");				
			}
			buf.append(System.getProperty("line.separator"));
		}
		
		buf.append("     ],");
		buf.append(System.getProperty("line.separator"));
		buf.append("     tbar:[");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'button',	 id:'add',		text:'添加',iconCls:'Add'},'-',");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'button',	 id:'update',	text:'修改',iconCls:'Dvdedit'},'-',");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'button',	 id:'delete',	text:'删除',iconCls:'Delete'},");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'tbfill'},");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype: 'displayfield', value:'标题'},                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'textfield', id:'keyword', enableKeyEvents:true,listeners:{       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        	  change:function(obj, newValue, oldVale, eOpts){                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        		  if(obj.getValue().length == 0){                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  var store = obj.up('"+toName+"List').store;                       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  Ext.apply(store.proxy.extraParams, {keyName:''});            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    				  store.load({limit:limit});                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        		  }                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        	  },                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        	  keypress:function(obj, e, eOpts){                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        		  if(e.getKey()==13){                                              ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  var store = obj.up(\""+toName+"List\").store;                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  var key = obj.getValue();                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  if(key.length == 0){                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        				  return ;                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  }                                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  store.removeAll(true);                                       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  store.getProxy().url = baseUrl + '/"+spName+"/ts_getGrid.l';          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  Ext.apply(store.proxy.extraParams, {keyName:key});           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  store.load({                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("  			    		params:{limit:limit}                                       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			  });                                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        			                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        		  }                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("        	  }                                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("          }},                                                                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("          {xtype:'button', text:'搜索', iconCls:'Magnifier', id:'search'}          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("                                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("     ],                                                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("     dockedItems:[{                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 xtype:'pagingtoolbar',                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 store:'"+toName+"."+upName+"Store',                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 dock:'bottom',                                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 displayInfo:true                                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("     }],                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("     listeners:{                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 beforerender:function(obj, eOpts){                                        ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    		 Ext.apply(obj.store.proxy.extraParams, {keyName: ''});");
		buf.append(System.getProperty("line.separator"));
		buf.append("    		 obj.store.load({limit:limit});                                        ");
		buf.append(System.getProperty("line.separator"));
		buf.append("    	 }                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("     }                                                                             ");
		buf.append(System.getProperty("line.separator"));
		buf.append("});                                                                                ");
		return buf.toString();
	}
	
	public static String createController(DbSchema bean){
		String toName = bean.getClassName().toLowerCase();
		String upName = bean.getClassName();
		String spName = getClassName(upName);
		
		StringBuffer buf = new StringBuffer();
		
		buf.append("Ext.define('GD.controller."+toName+"."+upName+"Controller',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("	extend:'Ext.app.Controller',");
		buf.append(System.getProperty("line.separator"));
		buf.append("	init:function(){");
		buf.append(System.getProperty("line.separator"));
		buf.append("		this.control({//控制事件处理");
		buf.append(System.getProperty("line.separator"));
		buf.append("			'"+toName+"List button[id=add]':{");
		buf.append(System.getProperty("line.separator"));
		buf.append("				click:function(btn,e,opt){");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var store = btn.up('"+toName+"List').store;");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var win = Ext.create('Ext.window.Window',{");
		buf.append(System.getProperty("line.separator"));
		buf.append("						title:'添加信息',");
		buf.append(System.getProperty("line.separator"));
		buf.append("						id:'add"+toName+"',");
		buf.append(System.getProperty("line.separator"));
		buf.append("						height:200,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						width:340,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						constrainHeader: true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						layout: 'fit',");
		buf.append(System.getProperty("line.separator"));
		buf.append("						iconCls:btn.iconCls,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						resizable : false,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						modal:true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						items:[{xtype:'"+toName+"Add',store:store}]");
		buf.append(System.getProperty("line.separator"));
		buf.append("					});");
		buf.append(System.getProperty("line.separator"));
		buf.append("					win.show();");
		buf.append(System.getProperty("line.separator"));
		buf.append("				}");
		buf.append(System.getProperty("line.separator"));
		buf.append("			},");
		buf.append(System.getProperty("line.separator"));
		buf.append("			'"+toName+"List button[id=update]':{");
		buf.append(System.getProperty("line.separator"));
		buf.append("				click:function(btn,e,opt){");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var data = btn.up('"+toName+"List').getSelectionModel().getSelection();");
		buf.append(System.getProperty("line.separator"));
		buf.append("					if(data.length <= 0){");
		buf.append(System.getProperty("line.separator"));
		buf.append("						MsgTip.msg('消息提示ʾ','请选择修改的一行');");
		buf.append(System.getProperty("line.separator"));
		buf.append("						return ;                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					}                                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var store = btn.up('"+toName+"List').store;                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var win = Ext.create('Ext.window.Window',{                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						title:'修改信息',                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						id:'update"+toName+"',                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						height:200,                                                                             ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						width:340,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						constrainHeader: true,");
		buf.append(System.getProperty("line.separator"));
		buf.append("						layout: 'fit',");
		buf.append(System.getProperty("line.separator"));
		buf.append("						iconCls:btn.iconCls,                                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						resizable : false,                                                                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						modal:true,                                                                             ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						items:[{xtype:'"+toName+"Update',store:store,model:data[0]}]                              ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					});                                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					win.show();                                                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				}                                                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("			},                                                                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("			'"+toName+"List button[id=delete]':{                                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				click:function(btn,e,opt){                                                                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var store = btn.up('"+toName+"List').store;                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var data = btn.up('"+toName+"List').getSelectionModel().getSelection();                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					if(data.length<=0){                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						MsgTip.msg('系统提示ʾ','系统提示');                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						return ;                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					}                                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var titles = [];                                                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var ids =[];                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					Ext.Array.each(data,function(record, index, Opts){                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						titles.push(record.get('username'));                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						ids.push(record.get('id'));                                                             ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					});                                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					Ext.MessageBox.confirm('系统提示ʾ','你是否删除:<span style=\"color:red\"><br>['                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("							+titles.join(']<br>[')+']</span><br>吗？',function(optional){                       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						if(optional=='yes'||optional == '是'){                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("							Ext.Ajax.request({                                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("								url: baseUrl + '/"+spName+"/ts_del.l',                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("								params:{ids:ids},                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("								success:function(response){                                                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var totalCount = store.getTotalCount();                                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var pageSize = store.pageSize;                                              ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var curPage = store.currentPage;                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var fromRecord = ((curPage - 1) * pageSize) + 1;                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var toRecord = Math.min(curPage * pageSize, totalCount);                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var totalOnCurPage = toRecord - fromRecord + 1;                             ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									var totalPageCount = Math.ceil(totalCount / pageSize);                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									//若当前页是最后一页，且不是仅有的一页，且删除的记录数是当前页上的所有记录数");
		buf.append(System.getProperty("line.separator"));
		buf.append("									if (curPage== totalPageCount && totalPageCount != 1)                        ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									{                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("										store.currentPage--;                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									}                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									store.load();                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									MsgTip.msg('消息提示ʾ','删除成功');                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("								},failure:function(response){                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("									Ext.MessageBox.alert('系统提示ʾ','由于网络原因，请求发送失败！');            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("								}                                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("							});                                                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("						}                                                                                       ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					});                                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				}                                                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("			},                                                                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("			'"+toName+"List button[id=search]':{                                                                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				click:function(btn,e,opt){					                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("					var store = btn.up('"+toName+"List').store;                                                  ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	var key = Ext.getCmp('keyword').getValue();                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	                                                                                            ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	if(key.length == 0){                                                                        ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  		return ;                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	}                                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	store.removeAll(true);                                                                      ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	store.getProxy().url = baseUrl + '/"+spName+"/ts_getGrid.l';                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	Ext.apply(store.proxy.extraParams, {keyName:key});                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	store.load({                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append(" 			    		params:{limit:limit}                                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("       			  	});                                                                                         ");
		buf.append(System.getProperty("line.separator"));
		buf.append("				}                                                                                               ");
		buf.append(System.getProperty("line.separator"));
		buf.append("			}                                                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("		});                                                                                                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	},                                                                                                          ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	views:[                                                                                                     ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	       '"+toName+"."+upName+"List',                                                                                   ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	       '"+toName+"."+upName+"Add',                                                                                    ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	       '"+toName+"."+upName+"Update'                                                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("   ],                                                                                                           ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	stores:['"+toName+"."+upName+"Store'],                                                                                ");
		buf.append(System.getProperty("line.separator"));
		buf.append("	models:['"+toName+"."+toName+"Model']                                                                                 ");
		buf.append(System.getProperty("line.separator"));
		buf.append("});                                                                                                             ");
                                                                                             
		
		return buf.toString();
	}
	
	public static String getClassName(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1,str.length());
	}

}
