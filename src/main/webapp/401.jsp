<%@ page language="java" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>SeinfraSin - Sistemas Integrados Seinfra</title>
    <link href="/auth/styles/error_pages.css" rel="stylesheet" type="text/css" />
    <link href="/ayth/imagens/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <!--[if IE 7]>
    <link href="/auth/styles/loginie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->
</head>

<body id="largura-minima">

	<!-- 1. Cabeçalho -->
    <div id="cabecalho" class="cab_verde">
    	<div>
        	<div>
            	<span class="calendario">
                	<!--Exibir data atual-->
                	<%= new java.text.SimpleDateFormat("dd/MM/yyyy, EEEEEEEE").format(new java.util.Date()) %>
                 </span>
            </div>
        </div>
    </div> <!-- FIM - 1. Cabeçalho -->
    
    <!-- 2. Caixa Conteudo -->
    <div id="corpo">
        <div class="borda-topo"><div><div></div></div></div>
            <div class="borda-corpo recuo">
                <div class="segundo-plano">
		    <div id="janela">
                        <div id="mensagem">
                            <div class="borda-topo"><div><div></div></div></div>
                            <div class="borda-corpo branco menscorp">
                                <div class="titulo">
                                    <img src="/auth/imagens/error-note.png" style="vertical-align: middle;"/>
                                    401 - ACESSO N&Atilde;O PERMITIDO
                                </div>
                                <div id="texto">
                                    <p>Voc&ecirc; n&atilde;o possui permiss&atilde;o para acessar o conte&uacute;do <br />do p&aacute;gina requisitada.</p>
                                    <p>Caso queira retirar alguma dúvida, <br />entre em contato com:</p>
                                    <p><strong>Célula de Informática</strong><br />
                                       3216-3745 / 3216-3739</p>
                                    <div class="limpa"></div>
                                </div>
                            </div>
                            <div class="borda-baixo"><div><div></div></div></div>
                        </div>
                    
                        <div id="logo">
                    	    <img src="/auth/imagens/logo.png"  />
                        </div>
                    </div>                
                <div class="limpa"></div>
            </div>
        </div>
        <div class="borda-baixo"><div><div></div></div></div>
    </div>
    <!-- FIM - 2. Caixa Conteudo -->
    
    <!-- 3. Rodapé -->
    <div id="rodape">
    	<div>
        	<div>
            	<div class="texto">&copy; <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> - Secretaria da Infraestrutura - Governo do Cear&aacute;. Todos os direitos reservados.</div>
            </div>
        </div>
    </div>
    <!-- FIM - 3. Rodapé -->

</body>

</html>
