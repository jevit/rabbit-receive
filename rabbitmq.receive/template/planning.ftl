

<html>
<head>
  <title>${table.title}</title>
  <style>
@page land { size:landscape;width:1000px; }
.landscapePage { page:land; width:1000px;}
table
{
	border-collapse:collapse;border-spacing:0px
	font-size:8px;
	width:100%;
	font-size:12px;
	text-align:center;
}

th,td,tr
{
	min-height: 20px;
  	min-width:10px;
	max-height: 20px;
  	max-width: 10px;
  	text-align:justify;
}

.repas
{
	background-image:url(toque.png); 
}

.left{
    float:left;
}

.right{
    position: absolute;
    right: 5px;
}

.rowtitle
{
	background: #9CF;
	font-weight:bold;
}

.tableaux{
   display: inline;
}

.colonneHeader1{
	color:black;
	background-color:#95B3D7;
	vertical-align:middle;
	border: 0.2px solid #969696;
	text-align: center;
	font-size:8px;
}
.colonneHeader2{
	color:black;
	background-color:#95B3D7;
	vertical-align:middle;
	border: 0px solid #969696;
	border-top: 0.2px solid #969696;
	text-align: left;
	font-size:8px;
}
.ligneHeader1{
	color:black;
	background-color:#ecf1f8;
  	min-width: 120px;
  	max-width: 120px;
	border: 0.2px solid #969696;
	line-height: 80%;
	text-align:left;
	font-size:8px;
}
.colonneDetail{
	color:black;
	vertical-align:middle;
	border: 0.2px solid #969696;
	text-align: center;
}

.colonneDetailRempli{
	background-color:#f466ff;
	color:black;
	border-left:0.2px solid #969696;
	border-right:0.2px solid #969696;
	border-top: 0.2px solid #969696;
	border-bottom: 0.2px solid #969696;
	text-align: center;
	vertical-align:middle;
}

.colonneDetailNoBorderLeft{
	border-left:none;
	border-right:none;
	vertical-align:middle;
}
.divRempli{
	background-color:#f466ff;
	height:8px;
	width:100%;
}
  </style>
</head>
<body class="landscapePage">
	  <!-- Titre de la page -->
      <div align="center">
	      <h2>${table.title}</h2>
	  </div>
	  <table cellpadding="0px">
	  	<tr>
	   		<!-- en-tête avec les noms des individus -->
	    	<td ></td> 
		    <!-- en-tête des colonnes -->
			<#list table.headerColumns as header>
					<#if header.show = 1>
		    			<!-- en-tête repas -->
						<#if header.type = 0>
			       			<td class="colonneHeader1" > <b>${header.name}</b> </td>
						</#if>
		    			<!-- en-tête des horaires -->
						<#if header.type = 1>
			  	  			<td class="colonneHeader2"  > 
								<#if header.name != "0" > <b>${header.name}</b> </#if>
			    			</td>
						</#if>
		    			<!-- en-tête cumul heures -->
						<#if header.type = 2>
		       				<td class="colonneHeader1" > <b>${header.name}</b> </td>
						</#if>
					</#if>
			</#list> 
	    </tr>
		<#list table.rowDatas as row>
	    <tr>
			<td class="ligneHeader1">
			       	<b> ${row.individu.prenom} ${row.individu.nom}  </b> 	<br/>
			      	<div style="font-size:6px !important;line-height: 100%;">${row.individu.age} ans</div >
			       	<div style="font-size:4px !important;line-height: 100%;">${row.individu.horaire}</div >
			</td>
			<#list row.columnDatas as column>
				<#if column.type = 0>
					<td class="colonneDetail">
					    <#if row.individu.repas = 1>
						   		<img src="toque.png" height="16" width="14"/>
					    <#else>
					       <!-- nothing -->
					    </#if>
					</td>
				<#elseif column.type = 1>
				  	<#if column.value = "1">
					  	<td class="colonneDetail"><div class="divRempli"></div></td>	
					<#else>
						<td class="colonneDetail"></td>
					</#if>
				<#elseif column.type = 2>
					<td class="colonneDetail">${column.value}</td>
				<#else>
					<!-- nothing -->
				</#if>
			</#list>
	     </tr>
		</#list>
		</table>  
</body>
</html> 