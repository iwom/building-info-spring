import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import {Floor} from '../floor';
import {FloorService} from './floor.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-floor-list',
  templateUrl: './floor-list.component.html',
  styleUrls: ['./floor-list.component.css']
})
export class FloorListComponent implements OnInit {
  floors: MatTableDataSource<Floor> = new MatTableDataSource();
  displayedColumns = ['id', 'name', 'area', 'cube', 'light', 'heating', 'detail'];

  constructor(
    private floorService: FloorService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.getFloors();
  }

  getFloors(): void {
    this.floorService.getFloors(this.route.params['value'].id).subscribe(resp => {
      this.floors.data = resp;
    });
  }

  goBack(): void {
    this.router.navigate(['buildings', this.route.params['value'].id]);
  }

  onDetailClick(floor: Floor): void {
    this.router.navigate(['buildings', this.route.params['value'].id, 'floors', floor.floorId]);
  }

}
