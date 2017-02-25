$(function(){  
 //文本框只能输入数字，并屏蔽输入法和粘贴  
 $.fn.numeral = function() {     
      $(this).css("ime-mode", "disabled");     
      this.bind("keypress",function(e) {     
      var code = (e.keyCode ? e.keyCode : e.which);  //兼容火狐 IE      
          if(!$.browser.msie&&(e.keyCode==0x8))  //火狐下不能使用退格键     
          {     
               return ;     
              }     
              return code >= 48 && code<= 57;     
      });     
      this.bind("blur", function() {     
          if (this.value.lastIndexOf(".") == (this.value.length - 1)) {     
              this.value = this.value.substr(0, this.value.length - 1);     
          } else if (isNaN(this.value)) {     
              this.value = "";     
          }     
      });     
      this.bind("paste", function() {     
          var s = clipboardData.getData('text');     
          if (!/\D/.test(s));     
          value = s.replace(/^0*/, '');     
          return false;     
      });     
      this.bind("dragenter", function() {     
          return false;     
      });     
      this.bind("keyup", function() {     
      if (/(^0+)/.test(this.value)) {     
          this.value = this.value.replace(/^0*/, '');     
          }     
      });     
  };
  //调用文本框的id  
  //$("#sjzf").numeral();   
});   