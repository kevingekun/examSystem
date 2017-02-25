function adaptimg(o,maxwidth){if(o.width>maxwidth) o.width=maxwidth; }
function zoomimg(o){var zoom=parseInt(o.style.zoom,10)||100;zoom+=event.wheelDelta/12;if (zoom>0) o.style.zoom=zoom+'%';return false;}
function originalimg(o){  o.style.zoom=100+'%';return false;}
