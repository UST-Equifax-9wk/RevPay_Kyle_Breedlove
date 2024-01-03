import { Component } from '@angular/core';
import { CurrentUserService } from '../current-user.service';

@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.css'
})
export class LandingPageComponent {
  currentUser:CurrentUserService;
  username:string;
  balance:number;
  constructor(currentUser:CurrentUserService){
    this.currentUser=currentUser;
    this.username=currentUser.getUsername();
    this.balance=currentUser.balance;
  }
}
