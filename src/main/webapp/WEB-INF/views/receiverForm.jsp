<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="comp/head.jsp" />
<jsp:include page="comp/header.jsp" />
<div class="container padding-header">
    <div class="row">
        <div class="grid-100">
            <h1>Get Pots from Social Media</h1>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/receiver" method="post">
        <div class="row">
            <div class="grid-100">
                <h5>Where to pull from?</h5>
            </div>
        </div>
        <div class="row">
            <div class="grid-50 tece">
                <input class="selectSocialMedia" name="getTwitter" type="checkbox" value="y"/> Get from Twitter
            </div>
            <div class="grid-50 tece">
                <input class="selectSocialMedia" name="getFacebook" type="checkbox" value="y"/> Get from Facebook
            </div>
        </div>
        <div class="row">
            <div class="grid-100">
                <h5>Modify Inputs</h5>
            </div>
        </div>
        <div class="row">
            <div class="grid-50 tece">
                <input name="aggregator" type="checkbox" value="y"/> Aggregate Messages?
            </div>
            <div class="grid-50 tece">
                <input name="filter" type="checkbox" value="y"/> Filter Messages?<br><br>
                <input name="filterString" type="text" placeholder="Filter Text"/>
            </div>
        </div>
        <div class="row">
            <div class="grid-100">
                <h5>Where shall the posts be forwarded to?</h5>
            </div>
        </div>
        <div class="row">
            <div class="grid-20 tece">
                <input id="saveLocal" name="saveLocal" type="checkbox" value="y"/> Save to Local File
            </div>
            <div id="pushFTP" class="grid-20 tece" style="visibility: hidden;">
                <input name="saveFTP" type="checkbox" value="y"/> Save to FTP
            </div>
            <div class="grid-20 tece">
                <input name="saveCSV" type="checkbox" value="y"/> Save to CSV
            </div>
            <div class="grid-20 tece">
                <input name="saveDB" type="checkbox" value="y"/> Save to Database
            </div>
            <div class="grid-20 tece">
                <input name="saveConsole" type="checkbox" value="y"/> Print in Console
            </div>
        </div>
        <div class="row">
            <div class="grid-100">
                <button class="button-green" type="submit">GetIT!</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    $("form").submit(function(){
        var checked = $(".selectSocialMedia:checked").length > 0;
        if (!checked){
            alert("Please check at least one social media to pull from");
            return false;
        }
    });

    $(document).ready(function() {
        $("#saveLocal").change(function(){
            if($(this).is(":checked")){
                $('#pushFTP').css('visibility', 'visible');
            }else{
                $('#pushFTP').css('visibility', 'hidden');
            }
        });
    });
</script>
<jsp:include page="comp/footer.jsp" />