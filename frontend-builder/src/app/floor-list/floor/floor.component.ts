import { Component, OnInit } from '@angular/core';
import {Floor} from '../../floor';
import {ActivatedRoute, Router} from '@angular/router';
import {FloorService} from '../floor.service';

@Component({
  selector: 'app-floor',
  templateUrl: './floor.component.html',
  styleUrls: ['./floor.component.css']
})
export class FloorComponent implements OnInit {
  floor: Floor = new Floor();
  constructor(
    private route: ActivatedRoute,
    private floorService: FloorService,
    private router: Router
  ) { }

  ngOnInit() {
    this.getFloor(this.route.params['value'].id,
      this.route.params['value'].floorId);
  }

  getFloor(id: number, floorId: number): void {
    this.floorService.getFloor(id, floorId).subscribe(resp => {
      this.floor = resp;
    });
  }


  goBack() {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors']);
  }

  showRooms() {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', this.route.params['value'].floorId, 'rooms']);
  }

}
