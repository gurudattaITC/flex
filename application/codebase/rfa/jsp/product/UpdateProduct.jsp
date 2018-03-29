<%-- Copyright (c) 2002 PTC Inc.   All Rights Reserved --%>


<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%-- //////////////////////////////// JSP HEADERS ////////////////////////////////////////--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%@page language="java"
       import="com.lcs.wc.client.Activities,
                com.lcs.wc.client.web.*,
                com.lcs.wc.util.*,
                com.lcs.wc.flextype.*,
                com.lcs.wc.placeholder.*,
                com.lcs.wc.product.*,
                wt.part.*,
		wt.util.*,
                java.util.*"
%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%-- //////////////////////////////// BEAN INITIALIZATIONS ///////////////////////////////--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<jsp:useBean id="productModel" scope="request" class="com.lcs.wc.product.LCSProductClientModel" />
<jsp:useBean id="tg" scope="request" class="com.lcs.wc.client.web.TableGenerator" />
<jsp:useBean id="fg" scope="request" class="com.lcs.wc.client.web.FormGenerator" />
<jsp:useBean id="flexg" scope="request" class="com.lcs.wc.client.web.FlexTypeGenerator" />
<jsp:useBean id="lcsContext" class="com.lcs.wc.client.ClientContext" scope="session"/>
<% flexg.setUpdate(true); %>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%-- ////////////////////////////// INITIALIZATION JSP CODE //////////////////////////////--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%!
    public static final String subURLFolder = LCSProperties.get("flexPLM.windchill.subURLFolderLocation");
    public static final boolean ENABLE_PAGE_NAVIGATION = LCSProperties.getBoolean("jsp.product.update.attributeGroupNavigation.enabled");
    public static final String defaultCharsetEncoding = LCSProperties.get("com.lcs.wc.util.CharsetFilter.Charset","UTF-8");
    public static final String OBJECT_THUMBNAIL_PLUGIN= PageManager.getPageURL("OBJECT_THUMBNAIL_PLUGIN", null);
	public static final String URL_CONTEXT = LCSProperties.get("flexPLM.urlContext.override");

%>
<%
   flexg.setScope(FootwearApparelFlexTypeScopeDefinition.PRODUCT_SCOPE);
   flexg.setLevel(FootwearApparelFlexTypeScopeDefinition.PRODUCT_LEVEL);
   String errorMessage = request.getParameter("errorMessage");
   LCSProduct product = productModel.getBusinessObject();
   FlexType type = product.getFlexType();
   boolean updateName =ACLHelper.hasEditAccess(type.getAttributeGroupObject(LCSProperties.get("com.lcs.wc.product.LCSProductNameAttributeGroup", "General Attributes"), false));

   Collection spls = (new PlaceholderQuery()).findSPLsWithPlaceholder(product);
   if(spls != null && !spls.isEmpty()){
       flexg.setDisableMappedAttributes(true);
   }

   String saveButton = WTMessage.getLocalizedMessage ( RB.MAIN, "save_Btn", RB.objA ) ;
   String cancelButton = WTMessage.getLocalizedMessage ( RB.MAIN, "cancel_Btn", RB.objA ) ;
   String updateProductPgHead = WTMessage.getLocalizedMessage ( RB.PRODUCT, "updateProduct_PG_HEAD", RB.objA ) ;
   String productIdentificationGrpTle = WTMessage.getLocalizedMessage ( RB.PRODUCT, "productId_GRP_TLE", RB.objA ) ;
   String typeLabel = WTMessage.getLocalizedMessage ( RB.MAIN, "type_LBL", RB.objA ) ;
   String seasonLabel = WTMessage.getLocalizedMessage ( RB.MAIN, "seasonColon_LBL", RB.objA ) ;
   String productLabel = WTMessage.getLocalizedMessage ( RB.MAIN, "product_LBL", RB.objA ) ;
   String skuLabel = WTMessage.getLocalizedMessage ( RB.SEASON, "sku_LBL", RB.objA ) ;
   String thumbnailLabel = WTMessage.getLocalizedMessage ( RB.DOCUMENT, "thumbnail_LBL", RB.objA ) ;
   String prodThumbnail = WTMessage.getLocalizedMessage ( RB.PRODUCT, "prodThumbnail_LBL", RB.objA ) ;
   String fileMustBe = WTMessage.getLocalizedMessage ( RB.IMAGE, "fileMustBe_ALRT",RB.objA ) ;
   String removeThumbnail = WTMessage.getLocalizedMessage ( RB.PRODUCT, "removeThumbnail_LBL", RB.objA ) ;

%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%-- /////////////////////////////////////// JAVSCRIPT ///////////////////////////////////--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
 
<script language="Javascript">
	
    function update(){
        document.MAINFORM.clearIfEmpty.value=document.MAINFORM.removeThumbnail.checked;
        if(validate()){
            document.MAINFORM.activity.value = '<%= Activities.UPDATE_PRODUCT %>';
            document.MAINFORM.action.value = 'SAVE';
			
 			submitForm();
        }
    }
	 
	function submitDragNDrop(){		 
		document.MAINFORM.clearIfEmpty.value=document.MAINFORM.removeThumbnail.checked;	 
		document.MAINFORM.activity.value = '<%= Activities.UPDATE_PRODUCT %>';
		document.MAINFORM.action.value = 'SAVE';
		submitForm()	 
    }

	
    function validate(){ 
	var productRange = document.MAINFORM.ptc_lng_1Input.value;
	//alert(productRange);
	if(productRange<0 || productRange>30) {
		alert('Invalid Product Range. Should be between 0 and 30');
		return false;
		
	}
      <%if(updateName){%>
      <%= flexg.drawFormValidation(type.getAttribute("productName")) %>
      <%}%>
      <%= flexg.generateFormValidation(product) %>
		if(document.MAINFORM.clearIfEmpty.value == 'false' && !hasContent(document.MAINFORM.imageFile.value)){
			document.MAINFORM.imageFile.value='';
		}else if(document.MAINFORM.clearIfEmpty.value == 'true'){
			document.MAINFORM.imageFile.value='';
		}

        var fileName = document.MAINFORM.imageFile.value;
        if(hasContent(fileName) && !validateFileName(fileName) ){
            alert('<%= FormatHelper.formatJavascriptString(fileMustBe , false)%>');
            return false;
	}
	
	
        return true;
    }
    
</script>
<input type="hidden" name="contextSKUId" value="<%=FormatHelper.encodeAndFormatForHTMLContent(request.getParameter("contextSKUId"))%>">
<input type="hidden" name="imageAttribute" value="partPrimaryImageURL">
<input type="hidden" name="clearIfEmpty" value="true">
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<%-- /////////////////////////////////////// HTML ////////////////////////////////////////--%>
<%-- /////////////////////////////////////////////////////////////////////////////////////--%>
<table width="100%">
    <tr>
        <td class="PAGEHEADING">
            <table width="100%">
                <tr>
                    <td class="PAGEHEADINGTITLE">
                       <%= updateProductPgHead %> 
                    </td>
                    <td class="button" align="right">
						<div id="saveButtonDiv" style="display:inline-block;">
                        <a id="saveButton" class="button" href="javascript:update()"><%= saveButton %></a></div>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a class="button" href="javascript:backCancel()"><%= cancelButton %></a>
						
                    </td>
               </tr>
           </table>
       </td>
    </tr>
    <tr>
        <td>
		<div id="fileDropZone">
         <%= tg.startGroupBorder() %>
         <%= tg.startTable() %>
         <%= tg.startGroupTitle() %>
            &nbsp;<%= productIdentificationGrpTle %>
         <%= tg.endTitle() %>
         <%= tg.startGroupContentTable() %>

          <% if(FormatHelper.hasContent(errorMessage)) { %>
         <tr>
            <td class="ERROR" colspan="4">
               <%= FormatHelper.encodeForHTMLContent(java.net.URLDecoder.decode(errorMessage, defaultCharsetEncoding)) %>
            </td>
         </tr>
         <% } %>
         <col width="15%"><col width="35%">
         <col width="15%"><col width="35%">
         <tr><td width="5%" class="LABEL">
                 <jsp:include page="<%=subURLFolder + OBJECT_THUMBNAIL_PLUGIN %>" flush="true">
                     <jsp:param name="thumbLocation" value="<%= product.getPartPrimaryImageURL() %>"/>
                     <jsp:param name="editable" value="false"/>
                     <jsp:param name="versionId" value="<%= FormatHelper.getVersionId(product) %>"/>
                     <jsp:param name="activity" value="<%= Activities.UPDATE_PRODUCT %>"/>
                     <jsp:param name="image" value="partPrimaryImageURL"/>
                     <jsp:param name="label" value="<%= prodThumbnail %>"/>
                     <jsp:param name="autoload" value="false"/>
                 </jsp:include>
             </td>
             <td width="95%" valign="top">
             <table>
                 <tr>
                 <%if(updateName){ %>
                     <%= flexg.drawFormWidget(type.getAttribute("productName"), product, true) %>
                 <%}else{ %>
                     <%= FormGenerator.createDisplay(type.getAttribute("productName").getAttDisplay(true), (String)product.getValue("productName"), FormatHelper.STRING_FORMAT) %>
                 <%} %>
                     <%= FormGenerator.createDisplay(typeLabel, type.getFullNameDisplay(false), FormatHelper.STRING_FORMAT) %>
                 </tr><tr>
                     <td class="FORMLABEL" nowrap>&nbsp;&nbsp;&nbsp;<%=thumbnailLabel %></td>
                     <td class="FORMELEMENT" style="text-align:left;display:inline-block;" ><input id="imageFile" name="imageFile" size="40" type="FILE" style="color:transparent; width:auto;" accept=".bmp,.gif,.png,.jpg,.jpeg"> <%@ include file="../uiComp/ThumbnailDragnDrop.jsp"%>
					</td>
                     <td class="FORMLABEL" nowrap colspan="2"><input id="removeThumbnail" type=checkbox>&nbsp;<%= removeThumbnail %></td>
					 					
					
                </tr>
            </table>
            </td>
         </tr>
         <%= tg.endContentTable() %>
         <%= tg.endTable() %>
         <%= tg.endBorder() %>
		 </div>
        </td>
    </tr>
   <tr>
      <td>
        <% if (ENABLE_PAGE_NAVIGATION) { 
                flexg.setAttGroupNavigation(true); 
           }
        %>
         <%= flexg.generateForm(product) %>
        <% if (ENABLE_PAGE_NAVIGATION) { 
                flexg.setAttGroupNavigation(false); 
           }
        %>
      </td>
   </tr>
</table>
<input type="hidden" id="partPrimaryImageURL" name="partPrimaryImageURL" value="" />