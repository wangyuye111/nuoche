/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/
CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
    // config.language = 'fr';
    config.uiColor = '#F1F5F2';
    // �ļ����
    config.filebrowserImageBrowseUrl = "./image.do";
    config.filebrowserFlashBrowseUrl = "./image.do";
    config.filebrowserImageUploadUrl  = "./image.do";
	
	config.autoParagraph = false;
    config.enterMode = CKEDITOR.ENTER_BR;
	config.shiftEnterMode = CKEDITOR.ENTER_P; 
};
