import { Component } from '@angular/core';
import { CurrentUserService } from '../current-user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  standalone: true,
  imports: [],
  templateUrl: './admin-page.component.html',
  styleUrl: './admin-page.component.css'
})
export class AdminPageComponent {
  currentUser: CurrentUserService;
  router: Router;

  constructor(currentUser: CurrentUserService, router:Router){
    this.currentUser=currentUser;
    this.router=router
  }

  ngOnInit(){
    if(!this.currentUser.admin){
      alert("You do not have access to this page, please log in")
      this.router.navigate(["user-login"])
      return;
    }
  }
}
