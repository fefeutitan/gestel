<%@ page language="java" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>SeinfraSin - Sistemas Integrados Seinfra</title>
    <link href="/auth/styles/error_pages.css" rel="stylesheet" type="text/css" />
    <link href="/auth/imagens/favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <!--[if IE 7]>
    <link href="/auth/styles/loginie7.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type="text/javascript" src="/auth/scripts/jquery-1.6.2.js"></script>
</head>

<body id="largura-minima">

    <!-- 1. Janela Modal Stack Trace -->
    <script type="text/javascript">
        $(document).ready(function() {

        //seleciona os elementos a com atributo name="modal"
        $('a[name=modal]').click(function(e) {
        //cancela o comportamento padrÃ£o do link
        e.preventDefault();

        //armazena o atributo href do link
        var id = $(this).attr('href');

        //armazena a largura e a altura da tela
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //Define largura e altura do div#mask iguais Ã¡s dimensÃµes da tela
        $('#mask').css({'width':'100%','height':'100%'});

        //efeito de transiÃ§Ã£o
        $('#mask').fadeIn(1000);
        $('#mask').fadeTo("slow",0.8);

        //armazena a largura e a altura da janela
        var winH = $(window).height();
        var winW = $(window).width();
        //centraliza na tela a janela popup
        $(id).css('top',  winH/2-$(id).height()/2);
        $(id).css('left', winW/2-$(id).width()/2);
        //efeito de transiÃ§Ã£o
        $(id).fadeIn(2000);
        });

        //se o botÃ£oo fechar for clicado
        $('.window .close').click(function (e) {
        //cancela o comportamento padrÃ£o do link
        e.preventDefault();
        $('#mask, .window').hide();
        });

        //se div#mask for clicado
        $('#mask').click(function () {
        $(this).hide();
        $('.window').hide();
        });
        });
    </script>

    <div id="boxes">

        <!-- #personalize sua janela modal aqui -->

        <div id="dialog" class="window">
            <!-- Botao para fechar a janela tem class="close" -->
            <a href="#" class="close">Fechar [X]</a><br />
            <!-- Titulo -->
            <b><%= exception.getLocalizedMessage() %></b>

            
            <b>Detalhe do erro:</b>
            <%
            Throwable e = exception;
            while (e != null) {
            %>
            <b><%= e.getMessage() %></b>
            <p style="padding-left: 10px;">
            <%
                for(StackTraceElement st : e.getStackTrace()) { %>
                    <br/><%= st %>
                <% } %>
            </p>
            
            <%
                e = e.getCause();
            }%>
                
        </div>

        <!-- Nao remova o div#mask, pois ele eh necessario para preencher toda a janela -->
        <div id="mask"></div>
    </div>
    <!-- FIM - 1. Janela Modal Stack Trace -->

    <!-- 2. Cabecalho -->
    <div id="cabecalho" class="cab_verde">
        <div>
            <div>
                <span class="calendario">
                    
                    <!--Exibir data atual-->
                    <%= new java.text.SimpleDateFormat("dd/MM/yyyy, EEEEEEEE").format(new java.util.Date()) %>
                 </span>
            </div>
        </div>
    </div> <!-- FIM - 2. CabeÃ§alho -->
    
    <!-- 3. Caixa Conteudo -->
    <div id="corpo">
        <div class="borda-topo"><div><div></div></div></div>
            <div class="borda-corpo recuo">
                <div class="segundo-plano">
            <div id="janela">
                        <div id="mensagem">
                            <div class="borda-topo"><div><div></div></div></div>
                            <div class="borda-corpo branco menscorp">
                                <div class="titulo titerro">
                                    <img src="/auth/imagens/error-alert.png" style="vertical-align: middle;"/>
                                    500 - ERRO
                                </div>
                                <div id="texto">
                                    <p>Ocorreu um erro durante a requisição do sistema.<br />
                                       Clique no botão voltar do seu navegador e tente realizar <br />a operação novamete.</p>
                                    <p>Caso o erro persista entre em contato com:</p>
                                    <p><strong>Célula de Informática</strong><br />
                                       3216-3745 / 3216-3739</p>
                                    <a href="#dialog" name="modal" class="stack-button">+ Stack Trace</a>
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
    <!-- FIM - 3. Caixa Conteudo -->
    
    <!-- 4. RodapÃ© -->
    <div id="rodape">
        <div>
            <div>
                <div class="texto">&copy; <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %> - Secretaria da Infraestrutura - Governo do Cear&aacute;. Todos os direitos reservados.</div>
            </div>
        </div>
    </div>
    <!-- FIM - 4. RodapÃ© -->

</body>

</html>
