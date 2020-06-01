function Entry_Customer_Krusty_Krab() {
  var spreadsheet = SpreadsheetApp.getActive();
  spreadsheet.getRange('D6').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1LeDeMFJ-mIwiAsIOXexI7MFFXUdGx72eYjNfqmMDnUY";"Customer Bern!C2")');
  spreadsheet.getRange('D8').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1LeDeMFJ-mIwiAsIOXexI7MFFXUdGx72eYjNfqmMDnUY";"Customer Bern!D2")\n');
  spreadsheet.getRange('D10').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1LeDeMFJ-mIwiAsIOXexI7MFFXUdGx72eYjNfqmMDnUY";"Customer Bern!E2")');
  spreadsheet.getRange('D11').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1LeDeMFJ-mIwiAsIOXexI7MFFXUdGx72eYjNfqmMDnUY";"Customer Bern!F2")');
  spreadsheet.getRange('D12').activate();
};

function Fill_Data() {
  var spreadsheet = SpreadsheetApp.getActive();
  spreadsheet.getRange('A17').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Snail_Garry!C19")');
  spreadsheet.getRange('B17').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Snail_Garry!B56")');
  spreadsheet.getRange('C17').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Snail_Garry!D58")');
  spreadsheet.getRange('D17').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Snail_Garry!D60")');
  spreadsheet.getRange('A18').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Squidward_Tentacles!C19"')
  .setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Squidward_Tentacles!C19")');
  spreadsheet.getRange('B18').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Squidward_Tentacles!B56")');
  spreadsheet.getRange('C18').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Squidward_Tentacles!D58")');
  spreadsheet.getRange('D18').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Squidward_Tentacles!D60")');
  spreadsheet.getRange('A19').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Sandy_Cheeks!C19")');
  spreadsheet.getRange('B19').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Sandy_Cheeks!B56")');
  spreadsheet.getRange('C19').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Sandy_Cheeks!D58")');
  spreadsheet.getRange('D19').activate();
  spreadsheet.getCurrentCell().setFormula('=IMPORTRANGE("https://docs.google.com/spreadsheets/d/1eIdlRWmVPg1Wy2J_SPxHuYvSj9gJDM4bHLr-B2DU0-k";"Sandy_Cheeks!D60")');
};

/**
 * @license MIT
 * 
 * © 2020 xfanatical.com. All Rights Reserved.
 * 
 * @since 1.3.1 Fix a landscape problem
 * @since 1.3.0 Support printing the entire spreadsheet as separate pdf files
 * @since 1.2.3 Add formatting for a single predefined area
 * @since 1.2.2 Add formatting for a single selected area
 * @since 1.2.1 Fix a reference issue of printing current sheet
 * @since 1.2.0 Support printing current sheet as pdf
 * @since 1.1.1 Fix an error for multi-language
 * @since 1.1.0 Support printing predefined areas as a pdf
 * @since 1.0.0 Support printing the entire spreadsheet as a pdf
 *              Support printing the selected areas as a pdf
 */

function onOpen() {
  SpreadsheetApp.getUi()
    .createAddonMenu()
    .addItem('Export all sheets', 'exportAsPDF')
    .addItem('Export all sheets as separate files', 'exportAllSheetsAsSeparatePDFs')
    .addItem('Export current sheet', 'exportCurrentSheetAsPDF')
    .addItem('Export selected area', 'exportPartAsPDF')
    .addItem('Export predefined area', 'exportNamedRangesAsPDF')
    .addToUi()
}

function _exportBlob(blob, fileName) {
  blob = blob.setName(fileName)
  var pdfFile = DriveApp.createFile(blob)
  
  // Display a modal dialog box with custom HtmlService content.
  const htmlOutput = HtmlService
    .createHtmlOutput('<p>Click to open <a href="' + pdfFile.getUrl() + '" target="_blank">' + fileName + '</a></p>')
    .setWidth(300)
    .setHeight(80)
  SpreadsheetApp.getUi().showModalDialog(htmlOutput, 'Export Successful')
}

function exportAsPDF() {
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  var blob = _getAsBlob(spreadsheet.getUrl())
  _exportBlob(blob, spreadsheet.getName())
}

function _getAsBlob(url, sheet, range) {
  var rangeParam = ''
  var sheetParam = ''
  if (range) {
    rangeParam =
      '&r1=' + (range.getRow() - 1)
      + '&r2=' + range.getLastRow()
      + '&c1=' + (range.getColumn() - 1)
      + '&c2=' + range.getLastColumn()
  }
  if (sheet) {
    sheetParam = '&gid=' + sheet.getSheetId()
  }
  var exportUrl = url.replace(/\/edit.*$/, '')
      + '/export?exportFormat=pdf&format=pdf'
      + '&size=LETTER'
      + '&portrait=true'
      + '&fitw=true'       
      + '&top_margin=0.75'              
      + '&bottom_margin=0.75'          
      + '&left_margin=0.7'             
      + '&right_margin=0.7'           
      + '&sheetnames=false&printtitle=false'
      + '&pagenum=false'
      + '&gridlines=true'
      + '&fzr=FALSE'      
      + sheetParam
      + rangeParam
      
  Logger.log('exportUrl=' + exportUrl)
  var response = UrlFetchApp.fetch(exportUrl, {
    headers: { 
      Authorization: 'Bearer ' +  ScriptApp.getOAuthToken(),
    },
  })
  
  return response.getBlob()
}

function exportAllSheetsAsSeparatePDFs() {
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  var files = []
  spreadsheet.getSheets().forEach(function (sheet) {
    spreadsheet.setActiveSheet(sheet)
    
    var blob = _getAsBlob(spreadsheet.getUrl(), sheet)
    var fileName = sheet.getName()
    blob = blob.setName(fileName)
    var pdfFile = DriveApp.createFile(blob)
    
    files.push({
      url: pdfFile.getUrl(),
      name: fileName,
    })
  })
  
  const htmlOutput = HtmlService
    .createHtmlOutput('<p>Click to open PDF files</p>'
      + '<ul>'
      + files.reduce(function (prev, file) {
        prev += '<li><a href="' + file.url + '" target="_blank">' + file.name + '</a></li>'
        return prev
      }, '')
      + '</ul>')
    .setWidth(300)
    .setHeight(150)
  SpreadsheetApp.getUi().showModalDialog(htmlOutput, 'Export Successful')
}

function exportCurrentSheetAsPDF() {
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  var currentSheet = SpreadsheetApp.getActiveSheet()
  
  var blob = _getAsBlob(spreadsheet.getUrl(), currentSheet)
  _exportBlob(blob, currentSheet.getName())
}

function exportPartAsPDF(predefinedRanges) {
  var ui = SpreadsheetApp.getUi()
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  
  var selectedRanges
  var fileSuffix
  if (predefinedRanges) {
    selectedRanges = predefinedRanges
    fileSuffix = '-predefined'
  } else {
    var activeRangeList = spreadsheet.getActiveRangeList()
    if (!activeRangeList) {
      ui.alert('Please select at least one range to export')
      return
    }
    selectedRanges = activeRangeList.getRanges()
    fileSuffix = '-selected'
  }
  
  if (selectedRanges.length === 1) {
    // special export with formatting
    var currentSheet = selectedRanges[0].getSheet()
    var blob = _getAsBlob(spreadsheet.getUrl(), currentSheet, selectedRanges[0])
    
    var fileName = spreadsheet.getName() + fileSuffix
    _exportBlob(blob, fileName)
    return
  }
  
  var tempSpreadsheet = SpreadsheetApp.create(spreadsheet.getName() + fileSuffix)
  var tempSheets = tempSpreadsheet.getSheets()
  var sheet1 = tempSheets.length > 0 ? tempSheets[0] : undefined
  SpreadsheetApp.setActiveSpreadsheet(tempSpreadsheet)
  
  for (var i = 0; i < selectedRanges.length; i++) {
    var selectedRange = selectedRanges[i]
    var originalSheet = selectedRange.getSheet()
    var originalSheetName = originalSheet.getName()
    
    var destSheet = tempSpreadsheet.getSheetByName(originalSheetName)
    if (!destSheet) {
      destSheet = tempSpreadsheet.insertSheet(originalSheetName)
    }
    
    Logger.log('a1notation=' + selectedRange.getA1Notation())
    var destRange = destSheet.getRange(selectedRange.getA1Notation())
    destRange.setValues(selectedRange.getValues())
    destRange.setTextStyles(selectedRange.getTextStyles())
    destRange.setBackgrounds(selectedRange.getBackgrounds())
    destRange.setFontColors(selectedRange.getFontColors())
    destRange.setFontFamilies(selectedRange.getFontFamilies())
    destRange.setFontLines(selectedRange.getFontLines())
    destRange.setFontStyles(selectedRange.getFontStyles())
    destRange.setFontWeights(selectedRange.getFontWeights())
    destRange.setHorizontalAlignments(selectedRange.getHorizontalAlignments())
    destRange.setNumberFormats(selectedRange.getNumberFormats())
    destRange.setTextDirections(selectedRange.getTextDirections())
    destRange.setTextRotations(selectedRange.getTextRotations())
    destRange.setVerticalAlignments(selectedRange.getVerticalAlignments())
    destRange.setWrapStrategies(selectedRange.getWrapStrategies())
  }
  
  // remove empty Sheet1
  if (sheet1) {
    Logger.log('lastcol = ' + sheet1.getLastColumn() + ',lastrow=' + sheet1.getLastRow())
    if (sheet1 && sheet1.getLastColumn() === 0 && sheet1.getLastRow() === 0) {
      tempSpreadsheet.deleteSheet(sheet1)
    }
  }
  
  exportAsPDF()
  SpreadsheetApp.setActiveSpreadsheet(spreadsheet)
  DriveApp.getFileById(tempSpreadsheet.getId()).setTrashed(true)
}

function exportNamedRangesAsPDF() {
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  var allNamedRanges = spreadsheet.getNamedRanges()
  var toPrintNamedRanges = []
  for (var i = 0; i < allNamedRanges.length; i++) {
    var namedRange = allNamedRanges[i]
    if (/^print_area_1/.test(namedRange.getName())) {
      Logger.log('found named range ' + namedRange.getName())
      toPrintNamedRanges.push(namedRange.getRange())
    }
  }
  if (toPrintNamedRanges.length === 0) {
    SpreadsheetApp.getUi().alert('No print areas found. Please add at least one \'print_area_1\' named range in the menu Data > Named ranges.')
    return
  } else {
    toPrintNamedRanges.sort(function (a, b) {
      return a.getSheet().getIndex() - b.getSheet().getIndex()
    })
    exportPartAsPDF(toPrintNamedRanges)
  }
}

function exportNamedRangesAsPDF1() {
  var spreadsheet = SpreadsheetApp.getActiveSpreadsheet()
  var allNamedRanges = spreadsheet.getNamedRanges()
  var toPrintNamedRanges = []
  for (var i = 0; i < allNamedRanges.length; i++) {
    var namedRange = allNamedRanges[i]
    if (/^print_area_2/.test(namedRange.getName())) {
      Logger.log('found named range ' + namedRange.getName())
      toPrintNamedRanges.push(namedRange.getRange())
    }
  }
  if (toPrintNamedRanges.length === 0) {
    SpreadsheetApp.getUi().alert('No print areas found. Please add at least one \'print_area_2\' named range in the menu Data > Named ranges.')
    return
  } else {
    toPrintNamedRanges.sort(function (a, b) {
      return a.getSheet().getIndex() - b.getSheet().getIndex()
    })
    exportPartAsPDF(toPrintNamedRanges)
  }
}
