import { Component } from '@angular/core';
import { ShowTransactionsComponent } from '../show-transactions/show-transactions.component';
import { RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [ShowTransactionsComponent, RouterOutlet, RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

}
