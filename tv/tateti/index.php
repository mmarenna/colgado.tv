<?php
$activa=0;
$ad='si';
$titulo='TATETI';
$descripcion='Ver TATETI por internet en vivo las 24hs toda la programaci&oacute;n online y sin l&iacute;mites.';
$programacion='http://tateti.tv/programas';
$player='<script type="text/javascript" src="/view/resources/player.js"></script><script type="text/javascript">jwplayer.key="e7ahbpd4sGi3zkDppT6q7tYUseKOkqu09/wC15Swiu8=";</script>
<object type="application/x-shockwave-flash" data="http://p.jwpcdn.com/6/8/jwplayer.flash.swf" width="660" height="400" bgcolor="#000000" id="player_421" name="player_421" tabindex="0"><param name="allowfullscreen" value="true" /><param name="allowscriptaccess" value="always" /><param name="seamlesstabbing" value="true" /><param name="wmode" value="opaque" /></object><div id="player_421_sharing" style="position: absolute; top: 0px; z-index: 10;">
</div>
<script type="text/javascript">
  jwplayer("player_421").setup({
    file: "http://ionlive1-lh.akamaihd.net/i/tateti_1@126174/master.m3u8",
	title: "America - Colgado.TV",
    width: "800",
    height: "450",
    image: "",
    stretching: "exactfit",        
    autostart: "true",
  });
</script>';
include_once'../../controller/prepare.php';	
?>