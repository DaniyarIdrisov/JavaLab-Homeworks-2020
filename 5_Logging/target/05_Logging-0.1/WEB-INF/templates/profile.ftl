<#ftl encoding="UTF-8"/>
<#import "parts/base.ftl" as p/>
<@p.page>
    <title>Profile</title>


    <div class="main_container">
        <h1 style="text-align:center; margin-bottom: 2rem;">Your profile <span> <form name="my-form"
                                                                                      action="logout"
                                                                                      method="post"><button
                            type="submit" class="btn btn-link"><span>Logout</span></button></form></span></h1>

        <div class="my-container" style="width:64%; margin: auto;">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">Information about you:</div>
                        <div class="card-body">
                            <h5 class="card-title">Login: ${user.getLogin()}</h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</@p.page>